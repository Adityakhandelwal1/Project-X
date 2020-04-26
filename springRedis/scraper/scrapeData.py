from bs4 import BeautifulSoup as bs
from pip._vendor import requests
import pandas as pd



def scrape52week(url):
    
    r=requests.get(url)
    data=r.text
    soup=bs(data)
    
    for listing in soup.find_all('td',attrs={'data-test':'FIFTY_TWO_WK_RANGE-value'}):
        print(listing.text)
        return (listing.text)






def scrapeData():
    links=[]

    names=[]
    prices=[]
    changes=[]
    percentChanges=[]
    marketCaps=[]
    totalVolumes=[]
    circulatingSupplys=[]
    weekRanges52 = []
    
    for i in range(1):
        CryptoCurrenciesUrl = 'https://in.finance.yahoo.com/most-active?offset=1&items_per_page=10'
        r= requests.get(CryptoCurrenciesUrl)
        data=r.text
        soup=bs(data)
        #print(soup)
        for listing in soup.find_all('tr', attrs={'class':'simpTblRow'}):
            for link in listing.find_all('td', attrs={'aria-label':'Symbol'}):
                for a in link:
                    weekRanges52.append(scrape52week('https://in.finance.yahoo.com/' + a['href']))
                   

                
            for name in listing.find_all('td', attrs={'aria-label':'Name'}):
                names.append(name.text)
            for price in listing.find_all('td', attrs={'aria-label':'Price (intraday)'}):
                prices.append(price.find('span').text)
            for change in listing.find_all('td', attrs={'aria-label':'Change'}):
                changes.append(change.text)
            for percentChange in listing.find_all('td', attrs={'aria-label':'% change'}):
                percentChanges.append(percentChange.text)
            for marketCap in listing.find_all('td', attrs={'aria-label':'Market cap'}):
                marketCaps.append(marketCap.text)
            for totalVolume in listing.find_all('td', attrs={'aria-label':'Avg vol (3-month)'}):
                totalVolumes.append(totalVolume.text)
            for circulatingSupply in listing.find_all('td', attrs={'aria-label':'Volume'}):
                circulatingSupplys.append(circulatingSupply.text)
            for weekRange52 in listing.find_all('td', attrs={'aria-label':'52-week range'}):
                weekRanges52.append(weekRange52.text)
                

    
    
    while('' in weekRanges52) : 
        weekRanges52.remove('') 
    

                
    print(len(names))
    print(len(weekRanges52))
    
    print(weekRanges52)
        
    df = pd.DataFrame({"Names": names, "Prices": prices, "Change": changes, "% Change": percentChanges, "Market Cap": marketCaps, "Average Volume": totalVolumes,"Volume":circulatingSupplys, "52WeekRanges": weekRanges52})
    export_csv = df.to_csv(r'pythonOutput/output.csv', index=None)
    print(df)
    export_csv

