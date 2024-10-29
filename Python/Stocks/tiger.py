from tigeropen.common.consts import (Language,        # 语言
                                Market,           # 市场
                                BarPeriod,        # k线周期
                                QuoteRight)       # 复权类型
from tigeropen.tiger_open_config import TigerOpenClientConfig
from tigeropen.common.util.signature_utils import read_private_key
from tigeropen.quote.quote_client import QuoteClient
import config

def get_client_config():
    # 港股牌照需用 props_path 参数指定token路径，如 '/Users/xxx/xxx/', 如不指定则取当前路径
    # 必须使用关键字参数指定 props_path
    client_config = TigerOpenClientConfig(props_path='/props/tiger_mock/')
    client_config.private_key = read_private_key('/Users/beiouyexiasha/Documents/Adia/private_key.pem')
    client_config.tiger_id = config.tiger_id
    client_config.account = config.tiger_account
    return client_config

if __name__ == "__main__":
    # 调用上方定义的函数生成用户配置ClientConfig对象
    client_config = get_client_config()
    
    # 随后传入配置参数对象来初始化QuoteClient
    quote_client = QuoteClient(client_config)
    # permissions = quote_client.grab_quote_permission() 
    permissions = quote_client.get_quote_permission()
    #输出list类型的行情权限权限列表
    print(permissions)

    # 调用API查询股票行情
    # stock_price = quote_client.get_stock_briefs(['SPY'])

    # 查询行情函数会返回一个包含当前行情快照的pandas.DataFrame对象，见返回示例。具体字段含义参见get_stock_briefs方法说明
    # print(stock_price)

