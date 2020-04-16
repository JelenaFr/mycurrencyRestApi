
function getIndex(list, id) {
    for (var i = 0; i<list.length; i++){
        if(list[i].id ===id){
            return i;
        }
    }

    return -1;
}


var currencyRateApi = Vue.resource('/currencyRate{/id}');

Vue.component('currencyRate-form', {
    props: ['currencyRates', 'currencyRateAttr'],
    data: function () {
        return {
            code: '',
            rate: '',
            base: '',
            date: '',
            id: ''
        }
    },
    watch: {
        currencyRateAttr: function (newVal, oldVal) {
            this.code = newVal.code;
            this.rate = newVal.rate;
            this.base = newVal.base;
            this.date = newVal.date;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<div><input type="code" placeholder="Write code" v-model="code" /></div>' +
        '<div><input type="rate" placeholder="Write rate" v-model="rate" /></div>' +
        '<div><input type="base" placeholder="Write base" v-model="base" /></div>' +
        '<div><input type="date" placeholder="Write date" v-model="date" /></div>' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var currencyRate = {currency:{code: this.code, rate: this.rate}, base: this.base, date: this.date};


            if (this.id) {
                currencyRateApi.update({id: this.id}, currencyRate).then(result =>
                    result.json().then(data =>{
                        var index = getIndex(this.currencyRates, data.id);
                        this.currencyRates.splice(index, 1, data);
                        this.code = ''
                        this.rate = ''
                        this.base = ''
                        this.date = ''
                        this.id = ''
                    })
                )
            } else {
                currencyRateApi.save({}, currencyRate).then(result =>
                    result.json().then(data => {
                        this.currencyRates.push(data);
                        this.code = ''
                        this.rate = ''
                        this.base = ''
                        this.date = ''
                    })
                )
            }
        }
    }
});

Vue.component('currencyRate-row', {
    props: ['currencyRate', 'editMethod',"currencyRates" ],
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
                    this.currencyRates.splice(this.currencyRates.indexOf(this.currencyRate), 1)
                }
            })

        }
    }
});

Vue.component('currencyRates-list', {
    props: ['currencyRates'],
    data: function () {
        return {
            currencyRate: null
        }
    },
    template: '<div style="position: relative; width: 500px;">' +
        '<currencyRate-form :currencyRates="currencyRates" :currencyRateAttr="currencyRate" />' +
        '<currencyRate-row v-for="currencyRate in currencyRates" :key= "currencyRate.id" :currencyRate="currencyRate" '+
        ' :editMethod="editMethod" :currencyRates="currencyRates"/>' +
        '</div>',

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
    },
    created: function () {
        currencyRateApi.get().then(result =>
            result.json().then(data =>
                data.forEach(currencyRate => this.currencyRates.push(currencyRate))
            )
        )
    },
});