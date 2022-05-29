package ch_2.nutritionFacts;

/**
 * ①テレスコーピング・コンストラクタ・パターン
 */
public class NutritionFacts1 {

    // フィールド
    private final int servingSize;  // (ml)            必須
    private final int servings;     // (容器当たり)      必須
    private final int calories;     // (一食当たり)      オプション
    private final int fat;          // (g/一食当たり)    オプション
    private final int sodium;       // (mg/一食当たり)   オプション
    private final int carbohydrate; // (g/一食当たり)    オプション ※備忘「変数 '～' は初期化されていない可能性があります」が出ていた。

    public NutritionFacts1(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts1(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts1(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts1(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts1(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
