/// <reference types="Cypress" />

describe("Cadastro de restaurante", () => {

  it("dados do restaurante", () => {
    cy.visit('/')

    cy.get('.mt-3 > .btn').click();

    cy.get('#razaoSocial').type("Teste automazidado");

    cy.get('#cnpj').type("05.160.923/0001-24");

    cy.get('#telefone').type("(11) 10522-4255")

    cy.get('.btn').click();

  });

  it("endereço do restaurante", () => {
    cy.get('#cep').type("12216-300");

    cy.get('#sql_estado').select("35");

    cy.get('#sql_cidade').select("3534500");

    cy.get('#numero').type("95");

    cy.get('#referencia').type("Perto do mercado atacado");

    cy.get('.btn').click();
  });

  it("login do restaurante", () => {
    cy.get('#email').type("teste@automatizado.com");
    cy.get('#senha').type("123456789");
    cy.get('#confirmarSenha').type("123456789");

    cy.get('.btn').click();

  });

  it("finalizar cadastro", () => {
    cy.get('.btn').click();
  })


});

