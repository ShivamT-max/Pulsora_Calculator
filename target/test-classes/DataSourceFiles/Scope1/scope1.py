###
# This is a sample of how the Input Map script provided by the power user
#   We could have a PublissModule API to downolad this template
#   Then the power user must complete it and upload it as a particular InputMap version
###

import pandas as pd
import numpy as np

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
        metrics["NUM4"] = np.nansum((data[key]['EmissionFactor'].map(lambda x: float(x)).values * data[key]['Period to Date'].values)*data[key]['MultiplicationFactor'].values)
    return  metrics
