#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "Spring"

import mysql_util

def insert_demo():
    conn, cur = mysql_util.get_connect_cursor()
    sql = "insert into demo (demo_id, demo_name) values (5, 'jige')"
    mysql_util.exeute_insert_update_delete(cur, sql)
    mysql_util.commit_(conn)
    mysql_util.close_connect_cursor(conn, cur)

def query_demo():
    conn, cur = mysql_util.get_connect_cursor()
    sql = "select * from demo"
    result = mysql_util.execute_query(cur, sql)
    print(result)
    mysql_util.close_connect_cursor(conn, cur)

if __name__ == "__main__":
    # insert_demo()
    query_demo()