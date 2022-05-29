package ch_2.nutritionFacts;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;


// todo 後で書籍を見直し
public abstract class Pizza {

    public enum Topping { HAM, MUSHROOM, PEPPER, SAUSAGE }

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {

        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}

/**
 * 参考
 *
 *  抽象クラス
 *   ・直接インスタンス化できない
 *   ・抽象メソッドはすべて、オーバーライドする必要がある
 *   ・サブクラスでコンストラクタを記述する必要がある
 */
// 抽象クラス
abstract class AbstractClass {

    private String name;

    public AbstractClass(String name) {
        this.name = name;
    }

    // 抽象メソッドとして記述
    // 実装クラスのみからのアクセスにするため、protectedで定義
    abstract protected void abstractMethod();
}

// 実装クラス(サブクラス)
class ConcreteClass extends AbstractClass {

    // コンストラクタ
    public ConcreteClass() {
        super("名前");
    }

    // 実装クラスは、抽象メソッドを実装する必要あり
    public void abstractMethod() {

        System.out.println("Concrete class");
    }
}