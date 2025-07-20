package gift.option.service;

import gift.option.dto.OptionResponseDto;

import java.util.List;

public interface OptionService {
    List<OptionResponseDto> getOptionsByProductId(Long productId, Long memberId);
}
