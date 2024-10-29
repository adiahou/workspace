import yfinance as yf
from alpaca.data.live import StockDataStream
from alpaca.data.historical import StockHistoricalDataClient
from alpaca.data.requests import StockLatestQuoteRequest
from alpaca.data.requests import StockBarsRequest
from alpaca.data.timeframe import TimeFrame
import alpaca_trade_api as tradeapi
import pandas as pd
from datetime import datetime, timedelta
import pytz
import requests
import finnhub
import config

BASE_URL = 'https://paper-api.alpaca.markets'
us_tz = pytz.timezone('America/New_York')


def yfinance_how_to_use():
    #1. Fetching Historical Data
    data = yf.download("AAPL", start="2020-01-01", end="2021-01-01")
    print(data.head())

    #2. Ticker Object
    apple = yf.Ticker("AAPL")
    print(apple.info)  # General information about Apple Inc.

    #3. Getting Recent Data
    recent_data = yf.download("AAPL", period="5d")
    print(recent_data)

    #4. Fetching Data for Multiple Tickers
    multi_data = yf.download(["AAPL", "MSFT"], start="2020-01-01", end="2021-01-01")
    print(multi_data)

    #5. Adjusted Data Retrieval
    data = yf.download("AAPL", start="2020-01-01", end="2021-01-01", auto_adjust=True)
    print(data['Close'])  # This will show the adjusted close prices

    # 6. Interval-based Data Retrieval
    weekly_data = yf.download("AAPL", start="2020-01-01", end="2021-01-01", interval="1wk")
    print(weekly_data)

    # 7. Retrieving Dividends and Splits
    apple = yf.Ticker("AAPL")
    dividends = apple.dividends
    splits = apple.splits
    print(dividends, splits)

    # 8. Real-time Data
    apple = yf.Ticker("AAPL")
    print(apple.history(period="1d"))

# 能获得最新报价信息，但不确定是否有延迟，需要开盘跑验证
def alpaca_live():

    client = StockHistoricalDataClient(config.api_key_alpaca, config.secret_key_alpaca)
    request_params = StockLatestQuoteRequest(symbol_or_symbols='SPY')

    latest_quotes = client.get_stock_latest_quote(request_params)

    gld_latest = latest_quotes['SPY']
    us_time = gld_latest.timestamp.astimezone(us_tz)
    print(us_time, gld_latest)

#只能获取最近时间为昨天的历史数据，无法获得实时最新数据，最新数据需另付费。
def alpaca_api():
    BASE_URL = 'https://paper-api.alpaca.markets'
    api = tradeapi.REST(config.api_key_alpaca, config.secret_key_alpaca, BASE_URL, api_version='v2')
    # 设置时间范围
    end = datetime.now()
    start = end - timedelta(days=1)  # 获取过去一天的数据
    # 格式化时间为RFC3339格式，不包含小数秒
    start_time_str = start.strftime('%Y-%m-%dT%H:%M:%SZ')
    end_time_str = end.strftime('%Y-%m-%dT%H:%M:%SZ')

    # 股票代码
    symbol = 'spy'

    # 获取3分钟K线数据
    barset = api.get_bars(symbol, '5Min',start=start_time_str, end=end_time_str, limit=1000,sort='desc')

    return barset.df

#与alpaca_api()一致，只能获取最近时间为昨天的历史数据，无法获得实时最新数据，最新数据需另付费。
def alpaca_history():
    # keys required for stock historical data client
    client = StockHistoricalDataClient(config.api_key_alpaca, config.secret_key_alpaca)

    # 设置时间范围
    end = datetime.now()
    start = end - timedelta(days=1)  # 获取过去一天的数据
    # 格式化时间为RFC3339格式，不包含小数秒
    start_time_str = start.strftime('%Y-%m-%dT%H:%M:%SZ')
    end_time_str = end.strftime('%Y-%m-%dT%H:%M:%SZ')

    # multi symbol request - single symbol is similar
    multisymbol_request_params = StockBarsRequest(symbol_or_symbols='SPY',
                                                  timeframe=TimeFrame.Minute,
                                                  sort='desc',
                                                  start=start,
                                                  end=end
                                                  )

    bars = client.get_stock_bars(multisymbol_request_params)

    return bars.df
    
# 能免费获取历史n分钟k线数据，但不确定是否有延迟，需要开盘跑验证
# 不能查询历史某一天数据
def alpha_api():
    symbol = 'SPY'
    interval = '5min'
    url = f'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol={symbol}&interval={interval}&apikey={config.api_key_alpha}'

    response = requests.get(url)
    data = response.json()

    # 解析数据
    time_series = data['Time Series (5min)']
    df = pd.DataFrame.from_dict(time_series, orient='index')
    df = df.astype(float)
    df.to_csv('alpha_api_result.csv')

    return df

# price: $49.99/month
def finnhub_api():
    finnhub_client = finnhub.Client(api_key=config.api_key_finnhub)
    
    #实时报价数据免费
    res = finnhub_client.quote('SPY')

    # # 历史k线数据需付费
    # now = datetime.now()
    # end_time = int(now.timestamp())
    # start_time = end_time - 24 * 60 * 60  # 获取过去 3 小时的数据
    # res = finnhub_client.stock_candles('SPY', '5', start_time, end_time)

    return res

if __name__ == "__main__":
    # alpaca_live()
    # data = alpaca_history()
    data = alpaca_api()
    # data = alpha_api()
    # data = finnhub_api()
    print(data)