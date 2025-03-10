/*
 * Copyright 2020-2021 LaynezCode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fithnity.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {


    public static int getDemands(java.sql.Date date) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM demande";
            PreparedStatement preparedStatementCustomers = DataSource.getInstance().getCnx().prepareStatement(sql);
            //preparedStatementCustomers.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = preparedStatementCustomers.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            count = 0;
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }


    public static int getOffres(java.sql.Date date) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM offre where dateOffre=? ";
            PreparedStatement preparedStetementProducts = DataSource.getInstance().getCnx().prepareStatement(sql);
            preparedStetementProducts.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = preparedStetementProducts.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            count = 0;
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
