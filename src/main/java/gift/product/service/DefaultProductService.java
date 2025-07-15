package gift.product.service;

import gift.product.dto.ProductRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.entity.Product;
import gift.common.exception.ForbiddenWordException;
import gift.common.exception.ProductNotFoundException;
import gift.product.repository.ProductRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 상품 생성
    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        if (requestDto.name().contains("카카오")) {
            throw new ForbiddenWordException("카카오");
        }

        Product product = new Product(requestDto.name(), requestDto.price(), requestDto.imageUrl());
        Product savedProduct = productRepository.save(product);

        return toResponseDto(savedProduct);
    }

    // 특정 상품 조회
    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return toResponseDto(product);
    }

    // 모든 상품 조회
    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // 특정 상품 수정
    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.update(requestDto.name(), requestDto.price(), requestDto.imageUrl());
        return toResponseDto(product);
    }

    // 특정 상품 삭제
    @Override
    public ProductResponseDto deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
        return toResponseDto(product);
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}
