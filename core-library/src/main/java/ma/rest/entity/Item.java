package ma.rest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents an item entity in the system.
 * This entity is mapped to the "item" table in the database.
 */
@Entity
@Table(name = "item")
public class Item {

    // Fields

    /**
     * Unique identifier for the item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    /**
     * Unique stock-keeping unit (SKU) for the item, maximum length of 64 characters.
     */
    @NotNull
    @Size(max = 64)
    @Column(length = 64, unique = true, nullable = false)
    private String stockKeepingUnit;

    /**
     * Name of the item, maximum length of 128 characters.
     */
    @NotNull
    @Size(max = 128)
    @Column(length = 128, nullable = false)
    private String itemName;

    /**
     * Price of the item, with a precision of 10 and scale of 2.
     */
    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal itemPrice;

    /**
     * Stock quantity of the item.
     */
    @NotNull
    @Column(nullable = false)
    private Integer itemStock;

    /**
     * Timestamp indicating the last update time of the item.
     */
    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant lastUpdated;

    /**
     * The category to which this item belongs.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category parentCategory;

    // Constructors

    /**
     * Default constructor initializing the updatedAt field to the current timestamp.
     */
    public Item() {
        this.lastUpdated = Instant.now();
    }

    // Getters and setters

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    // Overrides

    /**
     * Checks if this item is equal to another object based on id and SKU.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(stockKeepingUnit, item.stockKeepingUnit);
    }

    /**
     * Generates a hash code for the item based on id and SKU.
     */
    @Override
    public int hashCode() {
        return Objects.hash(itemId, stockKeepingUnit);
    }
}