
function getIndex(CurrencyRate, id) {
    for (var i = 0; i<currencyRate.size; i++){
        if(currencyRate[i].id ===id){
            return i;
        }
    }

    return -1;
}


var currencyRateApi = Vue.resource('/currencyRate{/id}');

Vue.component('currencyRate-form', {
    props: ['currencyRate', 'currencyRateAttr'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        currencyRateAttr: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<div><input type="text" placeholder="Write rate code" v-model="text" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var currencyRate = {text: this.text};

            if (this.id) {
                currencyRateApi.update({id: this.id}, currencyRate).then(result =>
                result.json().then(data =>{
                    var index = getIndex(this.currencyRates, data.id);
                    this.currencyRates.splice(index, 1, data);
                    this.text = ''
                    this.id = ''
                })
                )
            } else {
                currencyRateApi.save({}, currencyRate).then(result =>
                    result.json().then(date => {
                        this.currencyRates.push(data);
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('currencyRate-row', {
    props: ['currencyRate', 'editMethod',"currencyRates"],
    template: '<div>' +
        '<i>({{ currencyRate.id }})</i>{{ currencyRate.currency.code }}{{ currencyRate.currency.rate }}{{ currencyRate.base }}{{ currencyRate.date }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type = "button" value="Edit" @click="edit"/>' +
        '<input type = "button" value="Delete" @click="del"/>' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.currencyRate)
        },
        del: function () {
            currencyRateApi.remove({id: this.currencyRate.id}).then(result =>{
                if (result.ok){
                    this.currencyRates.slice(this.currencyRates.indexOf(this.currencyRate), 1)
                }
            })

        }
    }
});

Vue.component('currencyRates-list', {
    props: ['currencyRates'],
    data: function () {
        return {
            currencyRates: null
        }
    },
    template: '<div style="position: relative; width: 500px;">' +
        '<currencyRate-form :currencyRates="currencyRates" :currencyRateAttr="currencyRate" />' +
        '<currencyRate-row v-for="currencyRate in currencyRates" :key= "currencyRate.id" :currencyRate="currencyRate" '+
        ' :editMethod="editMethod" :currencyRates="currencyRates"/>' +
        '</div>',
    created: function () {
        currencyRateApi.get().then(result =>
            result.json().then(data =>
                data.forEach(currencyRate => this.currencyRates.push(currencyRate))
            )
        )
    },
    methods: {
        editMethod: function (currencyRate) {
            this.currencyRate = currencyRate;

        }
    }
});


var app = new Vue({
    el: '#app',
    template: '<currencyRates-list :currencyRates="currencyRates"/>',
    data: {
        currencyRates: []
    }
});