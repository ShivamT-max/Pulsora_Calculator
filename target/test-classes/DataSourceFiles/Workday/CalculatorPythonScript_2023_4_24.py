###
# This is a sample of how the Input Map script provided by the power user
#   We could have a PublissModule API to downolad this template
#   Then the power user must complete it and upload it as a particular InputMap version
###

import pandas as pd
import json


def transformations(ds, data):
    '''
    Transformations by datasource
        ds: the datasource name
        data: a pandas dataframe with the consolidated data
    Return
        df_trf: a new pandas dataframe with the transformed data

    '''
    
    df = pd.DataFrame(data)
    df = df[:]
    concat_df = pd.DataFrame()
    for col in df.columns:
        if len(concat_df) == 0:
            flat_df = df[col].apply(pd.Series)
            flat_df.columns = [str(s) + '_' + col for s in flat_df.columns]
            concat_df = flat_df
        else:
            flat_df = df[col].apply(pd.Series)
            flat_df.columns = [str(s) + '_' + col for s in flat_df.columns]
            concat_df = pd.concat([concat_df, flat_df], axis=1, join='inner')
            

    invoice_no, invoice_date, facility_code, start_date, end_date, amount_of_energy, orgId, userId, tenantId = [
        None for i in range(9)]
    invoices_list = []
    concat_df = concat_df[concat_df['0_documentDate'].notna()].reset_index()
    concat_df = concat_df[concat_df['defaultAddress_Supplier'].notna()].reset_index()

    invoice_no = concat_df[['invoiceNumber_supplierInvoiceDocument', '0_referenceID1']].agg('~'.join, axis=1).tolist() 
    
    facility_code = concat_df['0_ship_ToAddress_Full'].tolist()
    # facility_code = ['test franklin' for i in range(len(concat_df))]
    if 'nan' in facility_code:        
        facility_code = concat_df['descriptor_cf_SupplierInvoiceLineSupplierBillToAddress'].tolist()

    invoice_date = concat_df['0_documentDate'].tolist()
    concat_df['0_documentDate'] = concat_df['0_documentDate'].to_numpy().astype('datetime64')
    
    invoice_date  = concat_df['0_documentDate'].tolist()
    
    start_date = (concat_df['0_documentDate'].dt.floor('d') + pd.offsets.MonthEnd(0) - pd.offsets.MonthBegin(1))
    end_date = (concat_df['0_documentDate'].dt.floor('d') + pd.offsets.MonthEnd(0))
    amount_of_energy = concat_df['0_kWh'].tolist()

    for i in range(len(concat_df)):
        _invoice = {
                "calculator_name": "Purchased Electricity",
                "scope_name": "Scope 2",
                "invoices": [
                    {"energy_category": "Electricity",
                    "invoice_no": invoice_no[i],
                    "invoice_date": invoice_date[i].strftime("%Y-%m-%d"),
                    "facility_code": str(facility_code[i]),
                    "start_date": start_date.get(i).strftime('%Y-%m-%d'),
                    "end_date": end_date.get(i).strftime('%Y-%m-%d'),
                    "amount_of_energy": float(amount_of_energy[i]),
                    "orgId": None,
                    "userId": None,
                    "tenantId":None,
                    "evidenceFileUUID":"e4c4cebe-b8bf-4324-bca8-9abfc973a993",
                    "invoiceSource": "Swell Energy Utility API",
                    "source_data_unit": "kWh",
                    "is_custom_location": False,
                    "is_custom_market": False,
                    "cef_location":None,
                    "cef_market": None,
                    "Updatetype": "System"}
                    ]}
        invoices_list.append(_invoice)
    return invoices_list


def calculate_metric(metrics, data):
    '''
    Metric Calculations
        metrics: The dictionary of the metric to be calculated. Mention the metrics names.
        data: Mention the columns from data source that need to be mapped to the metrics names
    Return
        metrics: a dictionary with the metric value
    '''

    return metrics