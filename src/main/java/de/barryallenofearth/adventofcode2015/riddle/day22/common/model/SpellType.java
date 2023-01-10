package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public enum SpellType {
    MAGIC_MISSILE(0, 53, ((boss, wizard) -> boss.receiveDamage(4)), wizard -> {
    }),
    DRAIN(0, 73, ((boss, wizard) -> {
        boss.receiveDamage(2);
        wizard.setRemainingHealth(wizard.getRemainingHealth() + 2);
    }), wizard -> {
    }),
    SHIELD(6, 113,
            (boss, wizard) -> wizard.setArmor(7),
            wizard -> wizard.setArmor(0)),
    POISON(6, 173,
            (boss, wizard) -> boss.receiveDamage(3),
            (wizard -> {
            })),
    RECHARGE(5, 229, (boss, wizard) -> wizard.setMana(wizard.getMana() + 101), wizard -> {
    });

    private final int numberOfRounds;

    private final int manaCost;

    private final BiConsumer<Boss, Wizard> handleSpellEffect;
    private final Consumer<Wizard> endOfSpellAction;
}
