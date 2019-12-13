/// <reference types="Cypress" />

export default () => {
    describe("Login do restaurante", () => {

        cy.visit("/");

        // cy.fixture('login').as('loginRestaurante');
        cy.fixture('login').then((login) => {

            cy.get('#email').type(login.email);

            cy.get('#senha').type(login.password);

            cy.get('.mt-5 > .btn').click();
        });
    });
};