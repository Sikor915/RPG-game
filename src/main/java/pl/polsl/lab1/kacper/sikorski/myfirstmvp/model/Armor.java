package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.*;

/**
 *
 * @author sikor
 */
@Getter
@Setter
@AllArgsConstructor
public class Armor extends Item {

    private int damageReduction;

    @Builder
    public Armor(String name, int quantity, Type type, int damageReduction) {
        super(name, quantity, type); // Call the parent constructor
        this.damageReduction = damageReduction;
    }
}
