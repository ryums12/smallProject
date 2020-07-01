from finance import *


page , url, name = input_company()

pList = make_pList(url,page)

print_avg(name, pList)
