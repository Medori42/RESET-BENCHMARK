package ma.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * Represents a category entity in the system.
 * This entity is mapped to the "category" table in the database.
 */
@Entity
@Table(name = "category")
public class Category {

    // Fields

    /**
     * Unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    /**
     * Unique code for the category, maximum length of 32 characters.
     */
    @NotNull
    @Size(max = 32)
    @Column(length = 32, unique = true, nullable = false)
    private String categoryCode;

    /**
     * Name of the category, maximum length of 128 characters.
     */
    @NotNull
    @Size(max = 128)
    @Column(length = 128, nullable = false)
    private String categoryName;

    /**
     * Timestamp indicating the last update time of the category.
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant lastUpdated;

    /**
     * List of items associated with this category.
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Item> associatedItems;

    // Constructors

    /**
     * Default constructor initializing the updatedAt field to the current timestamp.
     */
    public Category() {
        this.lastUpdated = Instant.now();
    }

    // Getters and setters

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Item> getAssociatedItems() {
        return associatedItems;
    }

    public void setAssociatedItems(List<Item> associatedItems) {
        this.associatedItems = associatedItems;
    }

    // Overrides

    /**
     * Checks if this category is equal to another object based on id and code.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(categoryCode, category.categoryCode);
    }

    /**
     * Generates a hash code for the category based on id and code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryCode);
    }
}