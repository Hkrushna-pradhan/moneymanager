package in.hk.moneymanager.service;

import in.hk.moneymanager.dto.CategoryDto;
import in.hk.moneymanager.entity.CategoryEntity;
import in.hk.moneymanager.entity.ProfileEntity;
import in.hk.moneymanager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    // service methods
    // save the category
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        ProfileEntity profile = profileService.getCurrentProfile();
        if(categoryRepository.existsByNameAndProfileId(categoryDto.getName(), profile.getId())) {
            throw new RuntimeException("Category with this name already exists");
        }
        CategoryEntity newCategory = toEntity(categoryDto,profile);
        CategoryEntity savedCategory = categoryRepository.save(newCategory);
        return  toDTO(newCategory);
    }

    // get category for current user
//    public List<CategoryDto> getCategoriesForCurrentUser() {
//        ProfileEntity profile = profileService.getCurrentProfile();
//        List<CategoryEntity> categories = categoryRepository.findByProfileId(profile.getId());
//        return categories.stream().map(this::toDTO).toList();
//    }


    // get categories by type
//    public List<CategoryDto> getCategoriesByTypeForCurrentUser(String type) {
//        ProfileEntity profile = profileService.getCurrentProfile();
//        List<CategoryEntity> categories = categoryRepository.findByTypeAndProfileId(type, profile.getId());
//        return categories.stream().map(this::toDTO).toList();
//    }

    // update the category
//    public CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto) {
//        ProfileEntity profile = profileService.getCurrentProfile();
//        CategoryEntity existingCategory = categoryRepository.findByIdAndProfileId(categoryId, profile.getId()).orElseThrow(() -> new RuntimeException("Category not found or not accessible"));
//        existingCategory.setName(categoryDto.getName());
//        existingCategory.setIcon(categoryDto.getIcon());
//        existingCategory.setType(categoryDto.getType());
//
//        return toDTO(categoryRepository.save(existingCategory));
//    }

    // helper methods
    private CategoryEntity toEntity(CategoryDto categoryDto, ProfileEntity profileEntity) {
        return CategoryEntity.builder()
                .name(categoryDto.getName())
                .icon(categoryDto.getIcon())
                .profile(profileEntity)
                .type(categoryDto.getType())
                .build();
    }

    private CategoryDto toDTO(CategoryEntity entity) {
        return  CategoryDto.builder()
                .id(entity.getId())
                .profileId(entity.getProfile() != null ? entity.getProfile().getId() : null)
                .name(entity.getName())
                .icon(entity.getIcon())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .type(entity.getType())
                .build();
    }
}