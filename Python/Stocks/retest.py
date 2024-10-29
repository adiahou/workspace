""" 策略思路
spy kdj 5分钟线：
买入：
1.k和j都<d, && k和j距离第一次变小（即两个5分钟线的比较），&& k和j数值都在上涨。

卖出：
1.正常策略卖出： k和j都>d, && k和j距离第一次变小（即两个5分钟线的比较）， 
    同时判断当前10分钟线，K和J是否出于D上方，若是，不卖，若与D重合或者下方就卖.
2.止损： k和j都<d, && k和j距离第一次变小后变大（即三个5分钟线的比较），&& k和j数值都在下跌,
    同时判断当前10分钟线，K和J是否出于D上方，若是，不卖，若与D重合或者下方就卖.
2.止盈：
 """

import yfinance as yf
import matplotlib.pyplot as plt
import re
from datetime import time
import matplotlib.dates as mdates
import pandas as pd

# 下载美股数据，5分钟周期
def get_data(ticker,start,end,interval='5m'):
    target_tz = 'US/Eastern'
    data = yf.download(ticker, start=start, end=end, interval=interval)
    # 将数据的索引转换为目标时区
    data.index = data.index.tz_convert(target_tz)
    data = data[['High', 'Low', 'Close']]  # 只保留高价、低价和收盘价
    return data

def get_other_data_from_5min(data,min):
    # 设置日期时间为索引
    data.index = pd.to_datetime(data.index)

    # 重新采样数据为 10/15 分钟间隔
    data_new = data.resample(min+'T').agg({
        'High': 'max',
        'Low': 'min',
        'Close': 'last'
    })
    return data_new

# 计算KDJ指标
def calculate_kdj(data):
    # data['Low_Min'] = data['Low'].rolling(window=K_length, min_periods=1).min()
    # data['High_Max'] = data['High'].rolling(window=K_length, min_periods=1).max()
    
    # data['%K'] = 100 * (data['Close'] - data['Low_Min']) / (data['High_Max'] - data['Low_Min'])
    # data['%D'] = data['%K'].rolling(window=D_length).mean()
    # data['%J'] = 3 * data['%K'] - 2 * data['%D']
    # data.drop(columns=['Low_Min', 'High_Max'], inplace=True)

    # 计算最近9个周期的最高价和最低价
    data['High_9'] = data['High'].rolling(window=9, min_periods=1).max()
    data['Low_9'] = data['Low'].rolling(window=9, min_periods=1).min()

    # 计算RSV
    data['RSV'] = (data['Close'] - data['Low_9']) / (data['High_9'] - data['Low_9']) * 100

    # 初始化K和D
    data['%K'] = 50
    data['%D'] = 50

    # 计算K和D
    for i in range(1, len(data)):
        data.loc[data.index[i], '%K'] = 2/3 * data.loc[data.index[i-1], '%K'] + 1/3 * data.loc[data.index[i], 'RSV']
        data.loc[data.index[i], '%D'] = 2/3 * data.loc[data.index[i-1], '%D'] + 1/3 * data.loc[data.index[i], '%K']

    # 计算J
    data['%J'] = 3 * data['%K'] - 2 * data['%D']
    data.drop(columns=['High_9', 'Low_9', 'RSV'], inplace=True)

    return data

def plot_kdj(data,timebar,ticker):
    # 绘制 KDJ 指标
    plt.figure(figsize=(20, 10))

    date=data.index[0].strftime("%m-%d-%Y")
    plt.xticks(data.index)
    plt.xticks(rotation=90) 

    plt.plot(data.index, data['%K'], 'o-', label='%K', color='blue')
    plt.plot(data.index, data['%D'], 'o-', label='%D', color='red')
    plt.plot(data.index, data['%J'], 'o-', label='%J', color='green')
    plt.axhline(y=80, color='gray', linestyle='--', label='superby')
    plt.axhline(y=20, color='gray', linestyle='--', label='supersell')
    plt.legend(loc='upper left')
    plt.title(f'{ticker} {timebar} KDJ')
    plt.xlabel(date)
    plt.ylabel('KDJ value')

    plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%H:%M',tz='US/Eastern'))
    # plt.gca().xaxis.set_minor_formatter(mdates.DateFormatter('%H:%M',tz='US/Eastern'))
    # plt.gca().xaxis.set_major_locator(mdates.HourLocator(interval=1))
    # plt.gca().xaxis.set_minor_locator(mdates.MinuteLocator(interval=5))
    # plt.gcf().autofmt_xdate()

    plt.grid(True, which='both')
    plt.savefig(f'{ticker}_{timebar}_KDJ.png')

# 回测策略
def backtest_kdj(data_5m,data_10m, data_15m, initial_cash=10000):
    cash = initial_cash
    position = 0  # 当前持仓
    shares = 0  # 持股数量
    
    data_5m['Signal'] = 0  # 交易信号
    data_5m['Cash'] = 0  # 现金
    data_5m['Shares'] = 0  # 持股数量
    data_5m['Total Value'] = 0  # 总资产价值
    
    for i in range(1, len(data_5m)):
        # 买入信号
        k0=data_5m['%K'].iloc[i-1]
        j0=data_5m['%J'].iloc[i-1]
        d0=data_5m['%D'].iloc[i-1]
        k1=data_5m['%K'].iloc[i]
        j1=data_5m['%J'].iloc[i]
        d1=data_5m['%D'].iloc[i]
        
        if k0<d0 and j0<d0 and k1<d1 and j1<d1 and (k1-j1)<(k0-j0) and k1>k0 and j1>j0 and cash>0:
            shares = cash / data_5m['Close'].iloc[i]
            cash = 0
            position = 1
            data_5m['Signal'].iloc[i] = 1  # 记录买入信号

        # 卖出信号
        do_sell1= k0>d0 and j0>d0 and k1>d1 and j1>d1 and (j1-k1)<(j0-k0) and position == 1
        do_sell2= k0<d0 and j0<d0 and k1<d1 and j1<d1 and (k1-j1)>(k0-j0) and k1<k0 and j1<j0 and position == 1
             
        #取得该5m数据对应所在10m区间的数据
        index_5m = data_5m.index[i]
        index_10m = index_5m
        if index_5m.minute % 10 ==5:
            index_10m = index_5m - pd.Timedelta(minutes=5)
        k10=data_10m['%K'].loc[index_10m]     
        j10=data_10m['%J'].loc[index_10m]     
        d10=data_10m['%D'].loc[index_10m]   
        
        #取得该5m数据对应所在15m区间的数据
        index_5m = data_5m.index[i]
        index_15m = index_5m
        if index_5m.minute in [5,20,35,50]:
            index_15m = index_5m - pd.Timedelta(minutes=5)
        elif index_5m.minute in [10,25,40,55]:
            index_15m = index_5m - pd.Timedelta(minutes=10)
        k15=data_15m['%K'].loc[index_15m]     
        j15=data_15m['%J'].loc[index_15m]     
        d15=data_15m['%D'].loc[index_15m]    

        do_sell0 = k10<=d10 and j10<=d10 and k15<=d15 and j15<=d15

        if do_sell1 and do_sell0:
            cash = shares * data_10m['Close'].loc[index_10m]
            shares = 0
            position = 0
            data_5m['Signal'].iloc[i] = -1  # 记录卖出信号

        if do_sell2 and do_sell0:
            cash = shares * data_5m['Close'].iloc[i]
            shares = 0
            position = 0
            data_5m['Signal'].iloc[i] = -2  # 记录卖出信号
        
        # 更新资产信息
        data_5m['Cash'].iloc[i] = cash
        data_5m['Shares'].iloc[i] = shares
        data_5m['Total Value'].iloc[i] = cash + shares * data_5m['Close'].iloc[i]
    
    return data_5m

# 绘制结果
def plot_results(data):
    plt.figure(figsize=(14, 7))
    plt.plot(data.index, data['Total Value'], label='Total Value')
    plt.plot(data.index, data['Close'], label='Stock Price', alpha=0.3)

    plt.plot(data.index, data['%K'], label='%K', color='blue')
    plt.plot(data.index, data['%D'], label='%D', color='red')
    plt.plot(data.index, data['%J'], label='%J', color='green')
    plt.axhline(y=80, color='gray', linestyle='--', label='superby')
    plt.axhline(y=20, color='gray', linestyle='--', label='supersell')
    
    buy_signals = data[data['Signal'] == 1]
    sell_signals = data[data['Signal'] <0 ]
    
    plt.scatter(buy_signals.index, buy_signals['Close'], marker='^', color='green', alpha=1, label='Buy Signal')
    plt.scatter(sell_signals.index, sell_signals['Close'], marker='v', color='red', alpha=1, label='Sell Signal')
    
    plt.title('KDJ Indicator Backtest')
    plt.xlabel('Date')
    plt.ylabel('Value')
    plt.legend()
    plt.grid()
    plt.show()

# 主函数
def main():
    ticker = 'SPY'
    start = '2024-06-20'  
    end = '2024-06-21' 
    
    data_5min = get_data(ticker,start,end)
    data_5min = calculate_kdj(data_5min)
    plot_kdj(data_5min,'5min',ticker)

    data_10min = get_other_data_from_5min(data_5min,'10')
    data_10min = calculate_kdj(data_10min)
    plot_kdj(data_10min,'10min',ticker)
    data_10min.to_csv('data_10min.csv')

    data_15min = get_other_data_from_5min(data_5min,'15')
    data_15min = calculate_kdj(data_15min)
    plot_kdj(data_15min,'15min',ticker)
    data_15min.to_csv('data_15min.csv')

    data = backtest_kdj(data_5min,data_10min,data_15min)
    data.to_csv('result.csv')
    data_10min.to_csv('data_10min.csv')

    # plot_results(data)

if __name__ == "__main__":
    main()

        
