package gift.common.config;

import gift.member.entity.Role;
import gift.member.repository.MemberRepository;
import gift.product.entity.Product;
import gift.member.entity.Member;
import gift.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public DataInitializer(ProductRepository productRepository,
                           MemberRepository memberRepository) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 제품 초기 데이터
        productRepository.save(new Product("Cold Brew", 5100, "https://image.istarbucks.co.kr/upload/store/skuimg/2025/06/[9200000000038]_20250626095744579.jpg"));
        productRepository.save(new Product("Frappuccino", 6200, "https://image.istarbucks.co.kr/upload/store/skuimg/2025/06/[168016]_20250626113601250.jpg"));
        productRepository.save(new Product("Malcha Latte", 6300, "https://image.istarbucks.co.kr/upload/store/skuimg/2023/11/[9200000004954]_20231127093740911.jpg"));

        // 회원 초기 데이터
        memberRepository.save(new Member("user1@example.com", "password1", Role.USER));
        memberRepository.save(new Member("admin@example.com", "adminpwd", Role.ADMIN));
        memberRepository.save(new Member("user2@example.com", "password2", Role.USER));
    }
}
