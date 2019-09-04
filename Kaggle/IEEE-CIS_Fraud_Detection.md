데이터의 분포를 보고 각 특성에 따라 어떻게 전처리해야 되는지 정리해보자. 
===================================

# 데이터의 특성에 따라 어떻게 전처리 할 것 인가? 

* continuous variables 
numeric 이라고 명시되어 있지만 10개 이하의 distinct number로 구성된 column 존재하면 category로 변환
=> 모델에 따라 다름 (LGBM)

https://medium.com/@pushkarmandot/https-medium-com-pushkarmandot-what-is-lightgbm-how-to-implement-it-how-to-fine-tune-the-parameters-60347819b7fc

https://towardsdatascience.com/catboost-vs-light-gbm-vs-xgboost-5f93620723db


* missing value ratio >= 80
train["id_03"] 
=>  null이 아닌 값이 유의미 할 수 도 있음 


* 이미 정규화된 데이터는 정규화를 다시 적용할 필요 없다? 
train['id_07']


* category -> binary ?? 
=> PCA 관점 
