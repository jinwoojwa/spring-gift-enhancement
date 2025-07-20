package gift.option.service;

import gift.common.exception.ProductNotFoundException;
import gift.option.dto.OptionResponseDto;
import gift.option.entity.Option;
import gift.option.repository.OptionRepository;
import gift.product.entity.Product;
import gift.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DefaultOptionService implements OptionService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    public DefaultOptionService(ProductRepository productRepository,  OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public List<OptionResponseDto> getOptionsByProductId(Long productId, Long memberId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        List<Option> options = optionRepository.findByProduct(product);

        return options.stream()
                .map(OptionResponseDto::from)
                .toList();
    }
}
