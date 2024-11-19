###
# This is a sample of how the Input Map script provided by the power user
#   We could have a PublissModule API to downolad this template
#   Then the power user must complete it and upload it as a particular InputMap version
###

import pandas as pd

def transformations(ds, data):
    '''
    Transformations by datasource
        ds: the datasource name
        data: a pandas dataframe with the consolidated data
    Return
        df_trf: a new pandas dataframe with the transformed data
      
    '''
    return data

def calculate_metric(metrics,data):
    '''
    Metric Calculations
        metrics: The dictionary of the metric to be calculated. Mention the metrics names.
        data: Mention the columns from data source that need to be mapped to the metrics names
    Return
        metrics: a dictionary with the metric value
    '''
    for key in data:
        metrics["NUM5"] = data[key][data[key]["TITLE"] == r"Intern"].shape[0]
        metrics["NUM6"] = data[key][data[key]["TITLE"] == r"apprentice"].shape[0]
        metrics["NUM7"] = data[key][data[key]["GENDER"] == r"Female"].shape[0]
        
        
    return  metrics
    
    

# metrics = {}
# unique_metrics = ['Interns', 'Apprentice', 'Female']

# for metric in unique_metrics:
#     metrics[metric] = {'value':None, 'Explanation':None}

# data = {}
# import json
# # open('data.json')

# # import json
  
# # # Opening JSON file
# # f = open('data.json')
  
# # # returns JSON object as 
# # # a dictionary
# # data = json.load(f)

# _jsn = json.load(open("/home/prasadb/Downloads/jagdish_hr_001_working.json"))
# _list = _jsn["d"]["results"]
# for _obj in _list:
#     try:
#         del _obj["__metadata"]
#     except:
#         pass
    
# from io import StringIO
# df = pd.read_json(StringIO(json.dumps(_list)))
# data['ds1'] = df
# out_metric = calculate_metric(metrics, data)
# print(out_metric)
