
# coding: utf-8

# In[110]:

import pandas as pd
import numpy as np
import scipy as sp
from sklearn.model_selection import train_test_split
from scipy.sparse import csr_matrix
from sklearn.feature_selection import SelectFromModel
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import chi2
from sklearn.feature_selection import mutual_info_classif
from sklearn.tree import DecisionTreeClassifier


# In[10]:

df1 = pd.read_csv('YelpDataset_Reviews_Stars_20K.csv',header=None)


# In[114]:

def filterLen(docs, minlen=3):
    import re, string
    docs_raw4 = [re.sub('[\W_]+','', t) for t in docs.split() if len(t) >= minlen ]
    s = " "
    return s.join(docs_raw4)


# In[115]:

#filtered min len keywords
df1[1] = df1[1].apply(filterLen)


# In[118]:

X_train, X_test,y_train,y_test = df1[1][:15000],df1[1][15000:], df1[0][:15000], df1[0][15000:]


# In[30]:

from nltk import word_tokenize          
from nltk.stem import WordNetLemmatizer 
class LemmaTokenizer(object):
    def __init__(self):
         self.wnl = WordNetLemmatizer()
    def __call__(self, doc):
         return [self.wnl.lemmatize(t) for t in word_tokenize(doc)]


# In[42]:

def makeVectorizer(max_df,min_df,norm,stopwords,lemmatizer):
    from sklearn.feature_extraction.text import CountVectorizer
    from sklearn.feature_extraction.text import TfidfVectorizer
    vectorizer = TfidfVectorizer(lowercase = True,
                                stop_words = stopwords,
                                 max_df = max_df,
                                 min_df = min_df,
                                 norm = norm,
                                 tokenizer = lemmatizer)
    return vectorizer


# In[37]:

lemmatizer = LemmaTokenizer()


# In[197]:

vectorizer_idf = makeVectorizer(0.01,0.0005,'l2','english',lemmatizer)
vectorizer_idf_nolem=makeVectorizer(0.01,0.0005,'l2','english',None)


# In[198]:

vectorizer_idf.fit(X_train)
vectorizer_idf_nolem.fit(X_train)


# In[122]:

vectorizer_idf.get_feature_names()


# In[123]:

X_test_idf_l2_dtm = vectorizer_idf.transform(X_test)
X_train_idf_l2_dtm =vectorizer_idf.transform(X_train)


# In[199]:

X_test_idf_l2_dtm_nolem = vectorizer_idf_nolem.transform(X_test)
X_train_idf_l2_dtm_nolem =vectorizer_idf_nolem.transform(X_train)


# In[167]:

clfDT = DecisionTreeClassifier(max_depth=2000, class_weight='balanced',random_state=1)


# In[168]:

clfDT.fit(X_train_idf_l2_dtm,y_train)


# In[169]:

DTres = clfDT.predict(X_test_idf_l2_dtm)


# In[185]:

def accuracy(res, y_test):
    sum = 0
    for i, j in enumerate(res):
        if j != y_test[i]:
            sum +=j-y_test[i]
    return float(sum)/float(len(res))


# In[201]:

def testdepth(max_depth):
    score = []
    scores_nolem=[]
    for depth in max_depth:
        clfDT = DecisionTreeClassifier(max_depth=depth, class_weight='balanced',random_state=1)
        clfDT.fit(X_train_idf_l2_dtm,y_train)
        score.append(clfDT.score(X_test_idf_l2_dtm,y_test))
        clfDT.fit(X_train_idf_l2_dtm_nolem,y_train)
        scores_nolem.append(clfDT.score(X_test_idf_l2_dtm_nolem,y_test))
    return score, scores_nolem


# In[178]:

deltas =np.array(y_test) - DTres


# In[181]:

import matplotlib.pyplot as plt
# get_ipython().magic(u'matplotlib inline')


# In[217]:

n,bin,patches=plt.hist(deltas,normed=1)
plt.show()


# In[202]:

max_depthlist = np.linspace(10,1001,dtype=int)


# In[203]:

scores, scores_nolem = testdepth(max_depthlist)


# In[232]:

plt.plot(max_depthlist,scores,'r--',label='lemmatizer')
plt.plot(max_depthlist,scores_nolem,label='no_lemmatizer')
plt.ylabel('Accuracy')
plt.xlabel('max_depth')
plt.legend(loc='best')
plt.show()


