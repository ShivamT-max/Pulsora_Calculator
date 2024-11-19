###
# This is a sample of how the Input Map script provided by the power user
#   We could have a PublissModule API to downolad this template
#   Then the power user must complete it and upload it as a particular InputMap version
###

import pandas as pd
import datetime
import numpy as np

def transformations(ds, data):
    '''
    Transformations by datasource
        ds: the datasource name
        data: a pandas dataframe with the consolidated data
    Return
        df_trf: a new pandas dataframe with the transformed data
    '''
    df_trf = pd.DataFrame()
    #Fix column names
    df_trf = data.rename(columns = {'managementLevel.descriptor':'managementLevel', 'gender.descriptor':'gender', 'primaryWorkAddress_Country.descriptor':'region', 'workerType.descriptor':'type', 'terminationDate1':'termDate', 'raceEthnicity.descriptor':'race'}, inplace = False)

    #Filter rows by termDate greater than period
    df_trf['termDate'].replace('None', np.nan, inplace=True)

    df_trf['termDate'] = pd.to_datetime(df_trf['termDate']).fillna(datetime.datetime.utcnow())
    _period = datetime.datetime.strptime("2000-01-01", '%Y-%m-%d')
    df_trf = df_trf[df_trf['termDate'] >= _period]

    return df_trf

def calculate_metric(metrics,data):
    '''
    Metric Calculations
        metrics: The dictionary of the metric to be calculated
        data: a dictionary with all the transformed data by datasource
    Return
        metrics: a dictionary with the metric value
    '''
    for met in metrics.keys():
        metrics[met] = 0
        
    _bdlist = ['2 Chief Executive Officer', '3 Executive Vice President']
    _smlist = ['3 Executive Vice President', '4 Vice President']
    _mlist = ['5 Director', '6 Manager']
    _plist = ['7 Supervisor', '8 Individual Contributor']
    _asiaList = ['China','Hong Kong', 'Indonesia', 'Japan', 'Korea, Republic of', 'Malaysia', 
                'Philippines','Russian Federation', 'Singapore', 'Thailand']
    _emeaList = ['Austria', 'Belgium', 'Czechia', 'Denmark', 'Finland', 'France', 
                'Germany', 'Ireland', 'Italy', 'Luxembourg', 'Netherlands', 'Norway', 
                'South Africa', 'Spain', 'Sweden', 'Switzerland', 'United Arab Emirates',
                'United Kingdom']
    _latinamericaList = ['Brazil', 'Mexico']
    _uslist = ['United States of America']
    _polandlist = ['Poland']
    _newzealandlist = ['New Zealand']
    _indialist = ['India']
    _canadalist = ['Canada']
    _australialist = ['Australia']

    _totalemp = pd.DataFrame()
    for k, v in data.items():
        _totalemp = pd.concat([_totalemp,v], ignore_index=True)

    #Data preparation
    _countries = list(_totalemp['region'].unique())
    _countries_string = ','.join(_countries)
    _us = _totalemp.query(f"region == {_uslist}")
    _usmin = _us.query("race != ['White']")
    _usminae = len(_usmin)
    _usminbd = len(_usmin.query(f"managementLevel == {_bdlist}"))
    _usminsm = len(_usmin.query(f"managementLevel == {_smlist}"))
    _usminm = len(_usmin.query(f"managementLevel == {_mlist}"))
    _usminp = len(_usmin.query(f"managementLevel == {_plist}"))

    _bd = _totalemp.query(f"managementLevel == {_bdlist}")
    _bdae = len(_bd)
    _bdf = len(_bd.query("gender == 'Female'"))
    _bdm = len(_bd.query("gender == 'Male'"))
    _bdnd = len(_bd.query("gender == 'None'"))

    _ae = len(_totalemp)
    _aef = len(_totalemp.query("gender == 'Female'"))
    _aem = len(_totalemp.query("gender == 'Male'"))
    _aend = len(_totalemp.query("gender == 'None'"))

    _sm = _totalemp.query(f"managementLevel == {_smlist}")
    _sae = len(_sm)
    _smf = len(_sm.query("gender == 'Female'"))
    _smm = len(_sm.query("gender == 'Male'"))
    _smnd = len(_sm.query("gender == 'None'"))

    _m = _totalemp.query(f"managementLevel == {_mlist}")
    _mae = len(_m)
    _mf = len(_m.query("gender == 'Female'"))
    _mm = len(_m.query("gender == 'Male'"))
    _mnd = len(_m.query("gender == 'None'"))

    _p = _totalemp.query(f"managementLevel == {_plist}")
    _pae = len(_p)
    _pf = len(_p.query("gender == 'Female'"))
    _pm = len(_p.query("gender == 'Male'"))
    _pnd = len(_p.query("gender == 'None'"))

    _female = _totalemp.query("gender == ['Female']")
    _ftf = len(_female.query("type == ['Employee']"))
    _ptf = len(_female.query("type == ['Contingent Worker']"))

    _male = _totalemp.query("gender == ['Male']")
    _ftm = len(_male.query("type == ['Employee']"))
    _ptm = len(_male.query("type == ['Contingent Worker']"))

    _unknown = _totalemp.query("gender == ['None']")
    _ftu = len(_unknown.query("type == ['Employee']"))
    _ptu = len(_unknown.query("type == ['Contingent Worker']"))

    #Metrics
    _metrics = {}
    _metrics['Total number of employees'] = _ae
    _t1 = len(_totalemp.query(f"region == {_asiaList}"))
    _t2 = len(_totalemp.query(f"region == {_australialist}"))
    _t3 = len(_totalemp.query(f"region == {_canadalist}"))
    _t4 = len(_totalemp.query(f"region == {_emeaList}"))
    _t5 = len(_totalemp.query(f"region == {_indialist}"))
    _t6 = len(_totalemp.query(f"region == {_latinamericaList}"))
    _t7 = len(_totalemp.query(f"region == {_newzealandlist}"))
    _t8 = len(_totalemp.query(f"region == {_polandlist}"))
    _t9 = len(_us)
    _t10 = len(_totalemp.query(f"region == ['None']"))
    _metrics['Total number of employees by region: Asia'] = _t1 + _t10
#    _metrics['Total number of employees by region: Australia'] = _t2
    _metrics['Total number of employees by region: Canada'] = _t3
    _metrics['Total number of employees by region: EMEA (Excluding Poland)'] = _t4 + _t2 + _t7
    _metrics['Total number of employees by region: India'] = _t5
    _metrics['Total number of employees by region: Latin America'] = _t6
#    _metrics['Total number of employees by region: New Zealand'] = _t7
    _metrics['Total number of employees by region: Poland'] = _t8
    _metrics['Total number of employees by region: U.S.'] = _t9
    _metrics['Total number of employees by region:'] = _t1 + _t2 + _t3 + _t4 + _t5 + _t6 + _t7 + _t8 + _t9 + _t10
    
    if _bdae > 0:
        _metrics['Percentage of gender and racial/ethnic group representation for Board of Directors-Global Female Ratio'] = str(round(_bdf / _bdae * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Board of Directors-Global male Ratio'] = str(round(_bdm / _bdae * 100, 2)) + ' %'
        _metrics['Board of Directors (Global ND Ratio)'] = str(round(_bdnd / _bdae * 100, 2)) + ' %'
    else:
        _metrics['Percentage of gender and racial/ethnic group representation for Board of Directors-Global Female Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Board of Directors-Global male Ratio'] = '0 %'
        _metrics['Board of Directors (Global ND Ratio)'] = '0 %'

    if _ae > 0:
        _metrics['Percentage of gender and racial/ethnic group representation for All Employees-Global Female Ratio'] = str(round(_aef / _ae * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for All Employees-Global male Ratio'] = str(round(_aem / _ae * 100, 2)) + ' %'
        _metrics['All Employees (Global ND Ratio)'] = str(round(_aend / _ae * 100, 2)) + ' %'
    else:
        _metrics['Percentage of gender and racial/ethnic group representation for All Employees-Global Female Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for All Employees-Global male Ratio'] = '0 %'
        _metrics['All Employees (Global ND Ratio)'] = '0 %'

    if _sae > 0:
        _metrics['Percentage of gender and racial/ethnic group representation for Senior Management-Global Female Ratio'] = str(round(_smf / _sae * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Senior Management-Global Male Ratio'] = str(round(_smm / _sae * 100, 2)) + ' %'
        _metrics['Senior Management (Global ND Ratio)'] = str(round(_smnd / _sae * 100, 2)) + ' %'
    else:
        _metrics['Percentage of gender and racial/ethnic group representation for Senior Management-Global Female Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Senior Management-Global Male Ratio'] = '0 %'
        _metrics['Senior Management (Global ND Ratio)'] = '0 %'
        
    if _mae > 0:
        _metrics['Percentage of gender and racial/ethnic group representation for Management-Global Female Ratio'] = str(round(_mf / _mae * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Management-Global male Ratio'] = str(round(_mm / _mae * 100, 2)) + ' %'
        _metrics['Management (Global ND Ratio)'] = str(round(_mnd / _mae * 100, 2)) + ' %'
    else:
        _metrics['Percentage of gender and racial/ethnic group representation for Management-Global Female Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Management-Global male Ratio'] = '0 %'
        _metrics['Management (Global ND Ratio)'] = '0 %'

    if _pae > 0:
        _metrics['Percentage of gender and racial/ethnic group representation for Professionals-Global Female Ratio'] = str(round(_pf / _pae * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Professionals-Global male Ratio'] = str(round(_pm / _pae * 100, 2)) + ' %'
        _metrics['Professionals (Global ND Ratio)'] = str(round(_pnd / _pae * 100, 2)) + ' %'
    else:
        _metrics['Percentage of gender and racial/ethnic group representation for Professionals-Global Female Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Professionals-Global male Ratio'] = '0 %'
        _metrics['Professionals (Global ND Ratio)'] = '0 %'

    if len(_us) > 0:
        _metrics['Percentage of gender and racial/ethnic group representation for Board of Directors- U.S. Minority Ratio'] = str(round(_usminbd / len(_us) * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for All Employees-U.S. Minority Ratio'] = str(round(_usminae / len(_us) * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Senior Management-U.S. Minority Ratio'] = str(round(_usminsm / len(_us) * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Management-U.S. Minority Ratio'] = str(round(_usminm / len(_us) * 100, 2)) + ' %'
        _metrics['Percentage of gender and racial/ethnic group representation for Professionals-U.S. Minority Ratio'] = str(round(_usminp / len(_us) * 100, 2)) + ' %'
    else:
        _metrics['Percentage of gender and racial/ethnic group representation for Board of Directors- U.S. Minority Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for All Employees-U.S. Minority Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Senior Management-U.S. Minority Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Management-U.S. Minority Ratio'] = '0 %'
        _metrics['Percentage of gender and racial/ethnic group representation for Professionals-U.S. Minority Ratio'] = '0 %'

    _metrics['Total number of employees by employment type - Full time'] = _ftf + _ftm + _ftu
    _metrics['Total number of employees by employment type - Part time'] = _ptf + _ptm + _ptu
    _metrics['Total number of employees by employment type by gender: Female- Grand total'] = len(_female)
    _metrics['Total number of employees by employment type by gender: Female-Full time'] = _ftf
    _metrics['Total number of employees by employment type by gender: Female- Part time'] = _ptf
    _metrics['Total number of employees by employment type by gender: Male - Grand total'] = len(_male)
    _metrics['Total number of employees by employment type by gender: Male-Full time'] = _ftm
    _metrics['Total number of employees by employment type by gender: Male- Part time'] = _ptm
    _metrics['Total number of employees by employment type by gender: Unknown- Grand total'] = len(_unknown)
    _metrics['Total number of employees by employment type by gender: Unknown-Full time'] = _ftu
    _metrics['Total number of employees by employment type by gender: Unknown- Part time'] = _ptu
    _metrics['Total number of employees by employment type - Grand total'] = len(_female) + len(_male) + len(_unknown)
    
    _metrics['Location of operations'] = _countries_string
    _metrics['Number of countries of operations'] = len(_countries)
    
    for k, v in _metrics.items():
        if k in metrics.keys():
            metrics[k] = v
    
    return metrics
