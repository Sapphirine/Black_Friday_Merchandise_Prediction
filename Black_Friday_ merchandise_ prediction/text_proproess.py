import pandas as pd, numpy as np, csv

# fields = ['Purchase']
# df = pd.read_csv('train.csv', usecols=fields)
# print np.average(np.array(df['Purchase']))

fields = ['User_ID']
df = pd.read_csv('train.csv', usecols=fields)
df.to_csv(path_or_buf='./uid', header=True, index=None, sep=',', mode='a')

fields = ['Product_ID']
df = pd.read_csv('train.csv', usecols=fields)
df.to_csv(path_or_buf='./pid', header=True, index=None, sep=',', mode='a')

fields = ['User_ID', 'Product_ID', 'Purchase']
df = pd.read_csv('train.csv', usecols=fields)
df.to_csv(path_or_buf='./uid_pid_purchase.csv', header=True, index=None, sep=',', mode='a')
