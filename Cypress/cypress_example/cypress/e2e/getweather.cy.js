//<reference types="Cypress" />

describe('getweather',()=>{
    before('',()=>{
        cy.visit('https://www.google.com/')
    })

    it('test1',()=>{
        //search google with value "weather"
        cy.get('textarea[title="Search"]').type('weather{enter}')
        
        //get the result
        cy.get('span').contains('Results for').parent().find('span').last().then(($span) => {
            const location = $span.text
            cy.log('your location is:', location)
        })
        cy.get('div').contains('Weather').parent().find('div').eq(1).then(($div) => {
            const time = $div.text
            cy.log('current time is:', time)
        })
        cy.get('div').contains('Weather').parent().find('div>span').then(($span) => {
            const weather = $span.text

            //swich to °C and get the temprature
            cy.get('span').contains('°C').click()
            cy.wait(500)
            cy.get('span[style="display:inline"]').then(($span1) => {
                    const temprature = $span1.text
                    cy.log('current weather is:', weather + ' ' + temprature + '°C')
                })
        })
    })

    it.skip('test2',()=>{
    })

    after('',()=>{
    })
    
})