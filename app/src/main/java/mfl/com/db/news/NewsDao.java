package mfl.com.db.news;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao

public interface NewsDao {
    @Query("SELECT EXISTS (SELECT 1 FROM newsTable WHERE newsId = :id)")
    Single<Boolean> existsNewsId(int id);

    @Insert
    Completable insertNewsItem(NewsEntity newsEntity);

    @Query("delete From newsTable WHERE newsId =:newsId")
    Completable deleteNewsItem(int newsId);

    @Query("SELECT *  From newsTable ")
    Observable<List<NewsEntity>> getFavoriteNewsData();

//    @Insert
//    Completable insertProductList(List<ProductEntity> productEntityList);
//
//    @Update
//    Completable updateProductList(List<ProductEntity> productEntityList);
//

//
//    @Query("SELECT *  From productTable WHERE categoryName = :categoryName ")
//    Observable<List<ProductEntity>> getProductData(String categoryName);
//
//    @Query("delete From productTable")
//    Completable deleteAllProductData();
//
//    @Query("delete From productTable WHERE productId LIKE :productId")
//    Completable deleteProductData(String productId);
//
//    @Query("update productTable SET name = :namee, image = :imagee, cars = :cars,price = :pricee WHERE productId LIKE :productIdd")
//    Completable updateProductWithImage(String productIdd, String imagee, String namee, String cars, String pricee);
//
//    @Query("update productTable SET name = :namee,cars = :cars,price = :pricee  WHERE productId LIKE :productIdd")
//    Completable updateProductWithoutImage(String productIdd, String namee, String cars, String pricee);
//
//    @Query("SELECT * FROM ProductTable WHERE name LIKE '%'||:value ||'%'")
//    Observable<List<ProductEntity>> searchProduct(String value);
//
//    //cart Part
//    @Insert
//    Completable insertProductToCart(CartEntity cartEntity);
//
//
//
//    @Query("delete From cartTable")
//    Completable deleteCartData();
//
//    @Query("SELECT *  From cartTable")
//    Observable<List<CartEntity>> getCartData();
//
//

}
