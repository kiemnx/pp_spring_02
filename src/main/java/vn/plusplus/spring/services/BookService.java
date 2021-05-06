package vn.plusplus.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.spring.controller.response.BookListResponse;
import vn.plusplus.spring.model.BookEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    Connection connection;

    public BookListResponse getBookByCategoryID(Integer cateId, String order, String orderBy){
        BookListResponse response = new BookListResponse();
        List<BookEntity> data = null;
        String sql = "SELECT * FROM book WHERE category_id="+cateId+" ORDER BY "+orderBy+" "+order+";";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                if(data == null){
                    data = new ArrayList<>();
                }
                String title = rs.getString("title");
                String author = rs.getString("author");
                String avatar = rs.getString("avatar");
                BookEntity item = new BookEntity(title, author, avatar);
                data.add(item);
            }
            response.setData(data);
            response.setCode(200);
            response.setMessage("Success");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return response;
    }
}
