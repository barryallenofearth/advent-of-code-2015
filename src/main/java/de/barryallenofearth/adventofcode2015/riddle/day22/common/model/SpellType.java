package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public enum SpellType {
    MAGIC_MISSILE(0, 53, ((boss, youAsAFighter) -> boss.receiveDamage(4)), youAsAFighter -> {
    }),
    DRAIN(0, 73, ((boss, youAsAFighter) -> {
        boss.receiveDamage(2);
        youAsAFighter.setRemainingHealth(youAsAFighter.getRemainingHealth() + 2);
    }), youAsAFighter -> {
    }),
    SHIELD(6, 113,
            (boss, youAsAFighter) -> youAsAFighter.setArmor(7),
            youAsAFighter -> youAsAFighter.setArmor(0)),
    POISON(6, 173,
            (boss, youAsAFighter) -> boss.receiveDamage(3),
            (youAsAFighter -> {
            })),
    RECHARGE(5, 229, (boss, youAsAFighter) -> youAsAFighter.setMana(youAsAFighter.getMana() + 101), youAsAFighter -> {
    });

    private final int numberOfRounds;

    private final int manaCost;

    private final BiConsumer<Boss, YouAsAFighter> handleSpellEffect;
    private final Consumer<YouAsAFighter> endOfSpellAction;
}
