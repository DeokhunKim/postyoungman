package com.gift.server.repository;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Question;

import java.sql.*;
import java.util.ArrayList;

@org.springframework.stereotype.Repository
public class Repository {

    public Connection con = null;

    public Repository() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbFile = "db/db.sqlite";
            con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(con != null) {
                //try {con.close();}catch(Exception e) {}
            }
        }
    }

    public void storeStackoverflowGift(Gift gift) {
        try {
            Statement stat = con.createStatement();

            // 이미 파싱 한 건지 확인
            String validateQuery = "SELETE * FROM stackoverflow_question WHERE ID = " + gift.question.questionNum;
            ResultSet rs = stat.executeQuery(validateQuery);
            rs.last();
            if (rs.getRow() != 0) {
                return;
            }

            //question
            String questionQuery = "INSERT INTO stackoverflow_question VALUES (" +
                    gift.question.questionNum + ",\'" +
                    gift.question.title + "\',\'" +
                    gift.question.body + "\'," +
                    0 + ")";
            stat.execute(questionQuery);

            //answer
            for (Answer answer : gift.answerList) {
                String answerQuery = "INSERT INTO stackoverflow_answer(question_id, body) VALUES (" +
                        gift.question.questionNum + ",\'" +
                        answer.body + "\')";
                stat.execute(answerQuery);
            }
            stat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Gift getOneStackoverflowGift() {
        Gift gift = Gift.builder()
                .answerList(new ArrayList<>())
                .question(new Question())
                .build();

        //select
        try {
            String query = "SELECT q.id as questionNum, q.title as title, q.body as questionBody, a.body as answerBody\n" +
                    "FROM stackoverflow_question q, stackoverflow_answer a\n" +
                    "WHERE q.id = a.question_id\n" +
                    "AND q.isUpload = 0";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()) {
                Integer questionNum = rs.getInt("questionNum");
                String title = rs.getString("title");
                String questionBody = rs.getString("questionBody");
                String answerBody = rs.getString("answerBody");
                gift.question.questionNum = questionNum;
                gift.question.title = title;
                gift.question.body = questionBody;
                gift.answerList.add(new Answer(answerBody));
            }
            stat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //update
        try {
            Statement stat = con.createStatement();
            stat.execute("UPDATE stackoverflow_question SET isUpload = 1 WHERE id =" + gift.question.questionNum);
            stat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return gift;
    }

}
