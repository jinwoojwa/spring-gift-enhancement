package gift.common.config;

import gift.member.entity.Role;
import gift.member.repository.MemberRepository;
import gift.option.entity.Option;
import gift.option.repository.OptionRepository;
import gift.product.entity.Product;
import gift.member.entity.Member;
import gift.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OptionRepository optionRepository;

    public DataInitializer(ProductRepository productRepository,
                           MemberRepository memberRepository,
                           OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 제품 초기 데이터
        Product coldBrew = new Product("콜드브루", 4500, "https://image.istarbucks.co.kr/upload/store/skuimg/2025/06/[9200000000038]_20250626095744579.jpg");
        Product americano = new Product("아메리카노", 4000, "https://image.istarbucks.co.kr/upload/store/skuimg/2025/06/[110563]_20250626094354080.jpg");
        Product cappuccino = new Product("카푸치노", 5000, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[38]_20210415154821991.jpg");

        productRepository.save(coldBrew);
        productRepository.save(americano);
        productRepository.save(cappuccino);

        // 옵션 초기 데이터
        Option option1 = Option.of("Tall", 10, americano);
        Option optton2 = Option.of("Grande", 5, americano);
        Option option3 = Option.of("Tall", 3, coldBrew);
        Option option4 = Option.of("Venti", 2, cappuccino);

        optionRepository.save(option1);
        optionRepository.save(optton2);
        optionRepository.save(option3);
        optionRepository.save(option4);

        // 회원 초기 데이터
        memberRepository.save(new Member("user1@example.com", "password1", Role.USER));
        memberRepository.save(new Member("admin@example.com", "adminpwd", Role.ADMIN));
        memberRepository.save(new Member("user2@example.com", "password2", Role.USER));
    }
}
