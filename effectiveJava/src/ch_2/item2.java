package ch_2;

import ch_2.nutritionFacts.NutritionFacts1;
import ch_2.nutritionFacts.NutritionFacts2;
import ch_2.nutritionFacts.NutritionFacts3;

/**
 * 項目2 多くのコンストラクタパラメータに直面したときにはビルダーを検討する
 *
 * @author yamada.aoi
 */
public class item2 {

    public static void main(String[] args) {

        /** ①テレスコーピング・コンストラクタ・パターン */
        // 「NutritionFacts1」のインスタンス化
        NutritionFacts1 cocaCola1 = new NutritionFacts1(240, 8, 100, 35, 27);
        /**
         * 伝統的な手法
         *
         * 短所
         *  ・設定したくないパラメータでも、値を渡すことが強制される。
         *  ・パラメータの数が増えると、コードの読み書きが困難になる。
         *  ・同じ型のパラメータが長く続くと、分かりにくいバグの温床となる。
         */

        /** ②JavaBeansパターン */
        // 「NutritionFacts2」のインスタンス化
        NutritionFacts2 cocaCola2 = new NutritionFacts2();
        cocaCola2.setServingSize(240);
        cocaCola2.setServingSize(8);
        cocaCola2.setCalories(100);
        cocaCola2.setSodium(35);

        cocaCola2.getFat();

        cocaCola2.setCarbohydrate(27);
        /**
         * 長所
         *  ・多少コードが長いが、インスタンスの生成と、コードを読むことが容易。
         *
         * 短所
         *  ・インスタンス化後の生成途中に不整合が起こる可能性がある。
         *   →不整合な状態でオブジェクトを使用すると、バグを含むコードから離れた場所でエラーが起きる原因
         *  ・setterで内部状態を変更できるため、クラスを不変にすることができない。
         */


        /** ③ビルダーパターン */
        // 「NutritionFacts3」のインスタンス化
        // NutritionFacts3 cocaCola3 = new NutritionFacts3(); ※コンストラクタが、privateで宣言されているため、アクセス不可
        NutritionFacts3 cocaCola3 = new NutritionFacts3.Builder(240, 8).
                calories(100).sodium(35).carbohydrate(27).build();
        /**
         * 長所
         *  ・クラスが不変で、すべてのパラメータのデフォルト値は一か所にある。
         *  ・パラメータに名前があるため、読むことことが容易。
         */

    }
}
