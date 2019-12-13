import Login from "./login";

/// <reference types="Cypress" />

describe("Cadastro de restaurante", () => {

  beforeEach(() => {
    Login();
    cy.get('#cadastrar').click();
  });

  it("Cadastro de produto", () => {

    cy.get('#nome').type("lagosta");

    cy.get('#preco').type(200);

    cy.get('#desconto').type(5);

    cy.get('#desconto').type(5);

    cy.get('#descricao').type("Lagosta no Mar.");

    cy.get('#prox-campo').click();

  });

  it("Cadastro de categoria", () => {

    cy.get('#sql_categoria').select("2");

    cy.get('#salvar-categoria').click();

    cy.get('#sql_categoria').select("4");

    cy.get('#salvar-categoria').click();

    cy.get('#sql_categoria').select("1");

    cy.get('#salvar-categoria').click();

  });

  it("Finalizar", () =>{
    cy.get('.col-4 > .btn').click();
  })
});

