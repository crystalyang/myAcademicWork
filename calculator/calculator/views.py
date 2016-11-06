from django.http import HttpResponse
from django import forms
from django.shortcuts import render
from django.template import loader


def index(request):
    return HttpResponse("Hello, world")


class simpleform (forms.Form):
    stock_symbol= forms.CharField(required=True, help_text='A Stock Symbol')
    allotment = forms.DecimalField(required=True, help_text='Number of Shares')
    finalprice = forms.DecimalField(label="Final Price",required=True, help_text='Final share price (in dollars)')
    scommission = forms.DecimalField(label="Sell Commission",required=True, help_text='Sell commission (in dollars)')
    bcommission = forms.DecimalField(label="Buy Commission",required = True, help_text='Buy commission (in dollars)')
    iniprice = forms.DecimalField(label="Initial Price",required=True, help_text='Inital share price (in dollars)')
    tax = forms.DecimalField(required=True, help_text='Captial gain tax rate (in %)')

def Cal(request):
    form = simpleform()
    if request.POST:
        form = simpleform(request.POST)
        if form.is_valid():
            params = request.POST.copy()
            intkeys = params.keys()
            for key in intkeys:
                if params[key].isdigit():
                    params[key] = int(params[key])
            proceeds = params['allotment'] * params['finalprice']
            totalpurchaseprice = params['allotment'] * params['iniprice']
            buycom = params['bcommission']
            selcom = params['scommission']

            capgaintax = (params['tax']/100.0)*(params['finalprice']*params['allotment'] - totalpurchaseprice - buycom - selcom)
            cost = totalpurchaseprice + buycom + selcom + capgaintax
            netprofit = proceeds - cost
            roi = round(netprofit/float(cost),4)*100
            breakeven = (totalpurchaseprice + buycom + selcom)/float(params['allotment'])

            return render(request, 'result.html',{'proceeds':proceeds, 'cost':"{:,}".format(cost), 'purchase_price':"{:,}".format(totalpurchaseprice), 'buycom':buycom, 'selcom':selcom, 'tax':"{:,}".format(capgaintax), 'netprofit':"{:,}".format(netprofit), 'roi':roi, 'breakeven':breakeven,'allotment':params['allotment'],
                                                  'bprice':params['iniprice'], 'taxbasis':(params['finalprice']*params['allotment'] - totalpurchaseprice - buycom - selcom), 'taxrate':params['tax']})
    return render(request, 'base.html', {'form':form})