import com.alibaba.fastjson.JSON;
import com.ethan.entity.AllEntity;
import com.ethan.factory.Factory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class Book_Catagory_RoomTest {
    public static void main(String[] args) {

        List<AllEntity> book_catagory_room = null;
        AllEntity book_catagory_room1 = new AllEntity();
//        book_catagory_room1.setBook_ISBN("1");
//        book_catagory_room1.setBook_catagoryId(1);
//        book_catagory_room1.setBook_name("");
        System.out.println(JSON.toJSONString(book_catagory_room));
    }
//
//    Book_Catagory_RoomServiceImpl book_catagory_roomService = Factory.getBook_Catagory_RoomServiceInstance();
//    List<Book_Catagory_Room> book_catagory_room = null;
//    try {
//        book_catagory_room = book_catagory_roomService.allCatagory(1,2);
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    System.out.println(book_catagory_room.toString());
}
