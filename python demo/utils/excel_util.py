#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "Spring"

import openpyxl

def create_excel(header, body, file_path):
    # 获取 workbook 对象
    workbook = openpyxl.Workbook()
    # 获取 sheet 对象
    active_sheet = workbook.get_active_sheet()
    # 数据操作
    active_sheet.append(header)
    for item in body:
        active_sheet.append(item)
    # 文件保存
    workbook.save(filename=file_path)
