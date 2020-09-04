#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "Spring"

import plotly

# 折线图和点状图
def draw_line_graph():
    # 准备图轨数据
    trace_1 = plotly.graph_objs.Scatter(
        x = [1 ,3, 5, 7],
        y = [15,36,3,24],
        name="散点图",
        mode="markers"
    )
    trace_2 = plotly.graph_objs.Scatter(
        x=[1, 3, 5, 7],
        y=[25, 16, 10, 5],
        name="折线图",
    )

    line_data = [trace_1, trace_2]

    # 设置画布布局
    layout = plotly.graph_objs.Layout(title="折线图测试", xaxis={"title":"x"}, yaxis={"title":"y"})
    # 融合图轨数据和布局
    figure = plotly.graph_objs.Figure(data=line_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename="/Temp/line_graph.html", image="png")

# 柱形图
def draw_bar_graph():
    # 准备图轨数据
    trace_1 = plotly.graph_objs.Bar(
        x=[1, 3, 5, 7],
        y=[15, 36, 3, 24],
        name="python",
    )
    trace_2 = plotly.graph_objs.Bar(
        x=[1, 3, 5, 7],
        y=[9, 17, 6, 19],
        name="java",
    )
    trace_3 = plotly.graph_objs.Bar(
        x=[1, 3, 5, 7],
        y=[26, 42, 33, 27],
        name="c++",
    )

    bar_data = [trace_1, trace_2,trace_3]

    # 设置画布布局
    layout = plotly.graph_objs.Layout(title="柱状图测试", xaxis={"title": "季度"}, yaxis={"title": "产值"})
    # 融合图轨数据和布局
    figure = plotly.graph_objs.Figure(data=bar_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename="/Temp/bar_graph.html", image="png")

# 饼图
def fraw_pie_graph():
    # 准备图轨数据
    trace_1 = plotly.graph_objs.Pie(
        labels=["java", "python", "c++", "html", "php"],
        values=[35, 10, 30, 20, 5]
    )
    pie_data = [trace_1]

    # 设置画布布局
    layout = plotly.graph_objs.Layout(title="饼图测试")
    # 融合图轨数据和布局
    figure = plotly.graph_objs.Figure(data=pie_data, layout=layout)
    # 输出
    plotly.offline.plot(figure, filename="/Temp/pie_graph.html", image="png")

if __name__ == "__main__":
    # draw_line_graph()
    # draw_bar_graph()
    fraw_pie_graph()