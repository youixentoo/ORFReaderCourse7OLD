# -*- coding: utf-8 -*-
"""
Created on Mon Mar 26 13:29:06 2018

@author: thijs
"""

try:
    import mysql.connector
    from sys import argv
except Exception as exc:
    print(exc.with_traceback)
    
def main(args):
    databaseInsert(args[0], args[1], args[2], args[3], args[4])

    
def databaseInsert(host, user, db, passwd, query):
    try:
        # host = "???", user = "owe7_pg9", db="owe7_pg9", passwd="blaat1234"
        conn = mysql.connector.connect(host=host, user=user, db=db, passwd=passwd)
        cursor = conn.cursor()
        
        try:
            cursor.execute(query)
            cursor.execute("commit")
        except Exception as exc:
            print(exc.with_traceback)
   
    except Exception as exc:
        print(exc.with_traceback)    
        
        
main(argv)