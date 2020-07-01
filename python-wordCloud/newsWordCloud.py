from wordcloud import WordCloud
import matplotlib.pyplot as plt
import requests
from bs4 import *

url = 'https://news.naver.com'

wp = requests.get(url)
soup = BeautifulSoup(wp.text, 'html.parser')


titleList = soup.select('strong')
titleText = ""

for title in titleList:
    titleText += title.get_text() + " "


    

wordcloud = WordCloud(max_font_size=100,
                      width=800,
                      height=800,
                      font_path='c:/Windows/Fonts/HMFMPYUN.TTF', 
                      background_color="white")

wordcloud.generate(titleText)

plt.figure()
plt.imshow(wordcloud, interpolation='bilinear')
plt.axis('off')
plt.show()
