//package com.background_verification_app.backend.generator;
//
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class CandidateGenerator implements IdentifierGenerator {
//
//    @Override
//    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
//
//        String prefix = "CAND";
//        String suffix = "";
//        try{
//            Connection connection = sharedSessionContractImplementor.getJdbcConnectionAccess().obtainConnection();
//            Statement statement = connection.createStatement();
//            String sql = "select user_id from background_verification";
//            ResultSet resultSet = statement.executeQuery(sql);
//            if(resultSet.next()){
//                int sequenceValue = resultSet.getInt(1);
//                suffix = String.valueOf(sequenceValue);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return prefix + suffix;
//    }
//}
