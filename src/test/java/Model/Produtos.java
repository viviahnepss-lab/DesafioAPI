package Model;

import lombok.Data;

@Data
public class Produtos {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Double discountPercentage;
    private Double rating;
    private Integer stock;
    private String brand;
    private String category;
    private String thumbnail;
    }
