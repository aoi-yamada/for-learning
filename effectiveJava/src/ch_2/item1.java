package ch_2;

import java.math.BigInteger;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Random;

/**
 * 項目1 コンストラクタの代わりに staticファクトリメソッドを検討する
 *
 * @author yamada.aoi
 */
public class item1 {

    public static void main(String[] args) {

        // 下記、確率的素数(probable prime)を求めている

        /** コンストラクタ */
        Random random = new Random();
        new BigInteger(1, 1, random);

        /** staticファクトリメソッド */
        BigInteger.probablePrime(1, random);

        /**
         * ■staticファクトリメソッドの長所
         */

        /**
         * ①コンストラクタと異なり、名前を持つことで分かりやすい
         *  →クライアントのコードは読みやすくなる。
         */

        /**
         * ②呼び出しごとに新たなオブジェクトを生成する必要が無い
         *  →不変クラス(immutable object)となる。
         *  →「a=b」の場合のみ、「a.equals(b)」が成り立つ。 ※ enum型はこの保証がある。
         */

        /**
         * ③コンストラクタと異なり、メソッドの戻り値型の任意のサブタイプオブジェクトを返せる
         *  (例) java.util.Collections の emptyList() 「Collections.java」クラス参照
         *   ・emptyList() は、内部クラス(EmptyList)のインスタンスを返却する。 ※内部クラス(EmptyList)は pubic でないため、
         *   外部から隠蔽できる。
         *   ・emptyList() の戻り値の方は、Listインターフェース。
         *   →実装者にとって、EmptyListの使い方(API)を説明する必要が無く、Listインターフェースの使用方法のみを知っていればよい。
         *
         *    (参考)
         *    public static final List EMPTY_LIST = new EmptyList<>();
         *
         *    public static final <T> List<T> emptyList() {
         *         return (List<T>) EMPTY_LIST;
         *     }
         *
         *     private static class EmptyList<E>
         *         ・
         *         ・
         *         ・
         *    ↑ EmptyListクラスは、publicではない。
         *
         */

        /**
         * ④返されるオブジェクトのクラスは、入力パラメータの値に応じて呼び出しごとに変えられる
         *  →状況に応じて、複数のサブタイプの中から選定して返却できる。
         *
         *  (例) EnumSetクラス 「EnumSet.java」
         *  public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>
         *     implements Cloneable, java.io.Serializable
         * {
         *
         *     final Class<E> elementType;
         *
         *     final Enum<?>[] universe;
         *
         *     private static Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum<?>[0];
         *
         *     EnumSet(Class<E>elementType, Enum<?>[] universe) {
         *         this.elementType = elementType;
         *         this.universe    = universe;
         *     }
         *
         *     public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
         *     ・
         *     ・
         *     ・
         *
         * EnumSetクラスは、publicのコンストラクタは持っておらず、staticファクトリメソッドのみ。
         * 引数に応じて、返却するオブジェクトの型を自由に決められる。
         * ・enum型の要素が64個以下の場合は、RegularEnumSetインスタンスを返却。
         * ・enum型の要素が5個以上の場合は、JumboEnumSetインスタンスを返却。
         */

        /**
         * ⑤返されるオブジェクトのクラス(サブタイプ)は、そのstaticファクトリメソッドを含むクラスが書かれた時点で存在する必要が無い
         * →実行時にサブタイプが決定されればOK。
         *
         * JDBCの DriverManager.getConnection()が例
         *
         * 柔軟なstaticファクトリメソッドは、JDBCなどのサービスプロバイダフレームワークの基盤となる。
         */


        /**
         * ■staticファクトリメソッドの短所
         */

        /**
         * ① publicあるいは、protectedのコンストラクタを持たないクラスのサブクラスを作れない
         * （例）java.util.CollectionsのemptyList()では、EmptyListクラスが返却されるが、
         *  EmptyListは、privateなクラスのため、利用者はEmptyListのサブクラスを作るとことができない。
         *   →サブクラスを作りたくなるようなことはないかも。。。？
         */

        /**
         * ②プログラマがstaticファクトリメソッドを見つけることが難しい
         *  コンストラクタの場合は、Javadocでは別のセクションとなるため目立つが、
         *  staticファクトリメソッドは、メソッドの一覧に埋もれる。
         *   →命名をきちんとする。 ※命名規則に関しては、書籍参照
         */
    }

    /**
     * 備考
     * <p>
     * ○不変クラス(immutable objet)
     * ■メリット
     * ・複製コストが低い(Deep Copyしなくても、Shallow Copyで良い)
     * ・比較コストが低い(インスタンスの参照をチェックすれば良い)
     * ・スレッドセーフ(状態が常に同じため)
     * <p>
     * ■デメリット
     * ・手間がかかる(新規インスタンス生成必須)
     * <p>
     * 以下、参考ソース
     */
    private void immutable() {

        // Stringクラス
        String str = "A";   // 「A」
        str += "A";         // 「AA」
        str += "A";         // 「AAA」

        System.out.println(str);    // 「AAA」が出力

        // StringBuilderクラス
        StringBuilder sb = new StringBuilder();
        sb.append("A");     // 「A」
        sb.append("A");     // 「AA」
        sb.append("A");     // 「AAA」

        System.out.println(sb.toString());    // 「AAA」が出力
        /**
         * 上記、出力結果は同じだが、インスタンス生成のやり方が違う。
         *
         * ○ Stringクラスの場合
         *  「A」「AA」「AAA」と、Aが加算されるたびにインスタンスが生成されている。
         *  出力時には、「A」と「AA」のインスタンスは参照が外れているため、GCの対象となっている。
         *
         * ○ StringBuilderクラスの場合
         *  出力結果と同じインスタンスしか生成されていないため、インスタンスは「AAA」のみ。
         *
         * ・Stringクラスは、値を書き換えるたびにインスタンスが生成されている。
         *  →不変オブジェクト
         *
         * ・StringBuilderクラスは、ひとつのインスタンスの値が書き換わっている
         *  →可変オブジェクト
         *
         * ■不変オブジェクト
         *  インスタンスを複数生成するコストはかかるが、一つのオブジェクトは常に同じ値を保持し続ける。
         *   →他のオブジェクトに影響を出さずに済む
         *   →不変オブジェクトを使用すると、その値が変更されないことが保証される。(スレッドセーフになるため、マルチスレッドプログラミングに役立つ)
         *
         * ■可変オブジェクト
         *  インスタンスが一度しか生成されないため、パフォーマンスの改善につながる。
         *   →上記ソースの例は、「A」の加算は2回のみだが、10000回となると話が変わってくる
         *   →インタンスに変更を一回加えると、オブジェクトを参照しているオブジェクトにも影響がでてしまう点もある
         */
    }

    /**
     * 備考
     * <p>
     * ○ staticファクトリーメソッド
     * →クラスのインスタンスを返す単なるメソッド
     */
    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
    /**
     * 上記は、booleanという基本データ型を、Booleanオブジェクト参照へと変換している。
     *
     * クラスのインスタンスをクライアントが得るためには、publicなコンストラクタを通じる必要があるが、
     * staticファクトリメソッドを用いることで、staticなメソッドを通じてインスタンスを得ることができる。
     *
     * シングルトンにおけるstaticファクトリーメソッドの例は以下クラス参照
     * 「effectiveJava/src/ch_2/Singleton.java」
     */
}

