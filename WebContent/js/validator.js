"use strict"

class DeltagerController {

    constructor(root) {
        
        this.rootElm = root;
  
        this.fornavnValidate = this.fornavnValidate.bind(this)
        this.etternavnValidate = this.etternavnValidate.bind(this)
        this.mobilValidate = this.mobilValidate.bind(this)
        this.passordValidate = this.passordValidate.bind(this)
        this.passordValidate2 = this.passordValidate2.bind(this)
        this.submitClicked = this.submitClicked.bind(this)

        this.fornavn = root.querySelector('input[id="fornavn"]')
        this.fornavn.addEventListener("input", this.fornavnValidate)

        this.etternavn = root.querySelector('input[id="etternavn"]')
        this.etternavn.addEventListener("input", this.etternavnValidate)

        this.mobil = root.querySelector('input[id="mobil"]')
        this.mobil.addEventListener("input", this.mobilValidate)

        this.passord = root.querySelector('input[id="passord"]')
        this.passord.addEventListener("input", () => {
            this.passordValidate()
            this.passordValidate2()
        })

        this.passordRepetert = root.querySelector('input[id="passordRepetert"]')
        this.passordRepetert.addEventListener("input", this.passordValidate2)

        this.submitButton = root.querySelector('button[id="submit"]')
        this.submitButton.addEventListener("click", this.submitClicked)
        
        this.submitBoks = this.rootElm.querySelector('div[data-info="submit"]')
        this.initBoxes()
    }
    
    initBoxes(){
        this.submitBoks.classList.add("hidden")
        const passordBoks = this.rootElm.querySelector('div[data-info="passord"]')
        passordBoks.textContent = "Passord må være minst 8 tegn, men ikke mer enn 64 tegn. Rødt er ugyldig passord, gult er svakt og grønt er et sterkt passord."
        passordBoks.classList.add("hidden")
        
        this.passord.addEventListener("mouseout", () => {passordBoks.classList.toggle("hidden")})
        this.passord.addEventListener("mouseover", () => {passordBoks.classList.toggle("hidden")})
    }

    submitClicked(event) {
        if (this.validData()) {
            this.submitBoks.classList.add("hidden")
        } else {
            event.preventDefault()
            this.submitBoks.classList.remove("hidden")
        }
    }

    fornavnValidate() {
        const lengde = this.fornavn.value.length
        const regex = this.fornavn.value.match(/^[A-ZÆØÅ]{1}[a-zæøå]*( [A-ZÆØÅ]{1}|-[A-ZÆØÅ]{1}){0,1}[a-zæøå]*$/) != null
        const klasser = this.fornavn.classList

        if (lengde == 0) {
            klasser.remove("invalidInput", "validInput")
        }
        else if (lengde >= 2 && lengde <= 20 && regex) {
            klasser.remove("invalidInput")
            klasser.add("validInput")
        }
        else {
            klasser.remove("validInput")
            klasser.add("invalidInput")
        }
        return klasser.contains("validInput")
    }

    etternavnValidate() {
        
        const lengde = this.etternavn.value.length
        const regex = this.etternavn.value.match(/^[A-ZÆØÅ]{1}[a-zæøå]*(-[A-ZÆØÅ]{1}){0,1}[a-zæøå]*$/) != null
        const klasser = this.etternavn.classList

        if (lengde == 0) {
            klasser.remove("invalidInput", "validInput")
        }
        else if (lengde >= 2 && lengde <= 20 && regex) {
            klasser.remove("invalidInput")
            klasser.add("validInput")
        }
        else {
            klasser.remove("validInput")
            klasser.add("invalidInput")
        }
        return klasser.contains("validInput")
    }

    mobilValidate() {
        const lengde = this.mobil.value.length
        const regex = this.mobil.value.match(/^\d{8}$/) != null
        const klasser = this.mobil.classList

        if (lengde == 0) {
            klasser.remove("invalidInput", "validInput")
        }
        else if (regex) {
            klasser.remove("invalidInput")
            klasser.add("validInput")
        }
        else {
            klasser.remove("validInput")
            klasser.add("invalidInput")
        }
        return klasser.contains("validInput")
    }

    passordValidate() {
        const lengde = this.passord.value.length
        const klasser = this.passord.classList

        if (lengde == 0) {
            klasser.remove("weakPassword", "mediumPassword", "strongPassword")
        }
        else if (lengde < 8 || lengde > 64) {
            klasser.remove("mediumPassword", "strongPassword")
            klasser.add("weakPassword")
        }
        else if (lengde < 16) {
            klasser.remove("weakPassword", "strongPassword")
            klasser.add("mediumPassword")
        }
        else {
            klasser.remove("weakPassword", "mediumPassword")
            klasser.add("strongPassword")
        }
        return !klasser.contains("weakPassword")
    }

    passordValidate2() {
        const lengde = this.passordRepetert.value.length
        const klasser = this.passordRepetert.classList

        if (lengde == 0) {
            klasser.remove("invalidInput", "validInput")
        }
        else if (this.passord.value == this.passordRepetert.value) {
            klasser.remove("invalidInput")
            klasser.add("validInput")
        }
        else {
            klasser.remove("validInput")
            klasser.add("invalidInput")
        }
        return klasser.contains("validInput")
    }

    ingenKjonn() {
        const kjonn = this.rootElm.querySelector('span[data-kjonn]')
        const mann = kjonn.querySelector('input[id="mann"]')
        const kvinne = kjonn.querySelector('input[id="kvinne"]')
        return (mann.checked || kvinne.checked)
    }

    validData() {
        if (!this.fornavnValidate()) {
            this.submitBoks.textContent = "Ugyldig fornavn. Må være mellom 2 og 20 bokstaver. Kan inkludere mellomrom og bindestrek"
            return false;
        }
        else if (!this.etternavnValidate()){
            this.submitBoks.textContent = "Ugyldig etternavn. Må være mellom 2 og 20 bokstaver. Kan inkludere bindestrek"
            return false;
        }
        else if (!this.mobilValidate()) {
            this.submitBoks.textContent = "Ugyldig mobilnummer. Må være 8 siffer uten mellomrom"
            return false;
        }
        else if (!this.passordValidate()) {
            this.submitBoks.textContent = "Ugyldig passord. Må være minst 8 tegn, men ikke mer enn 64 tegn"
            return false;
        }
        else if (!this.passordValidate2()) {
            this.submitBoks.textContent = "Ugyldig repetert passord. Det repeterte passorder er ikke likt passordet"
            return false;
        } 
        else if (!this.ingenKjonn()) {
            this.submitBoks.textContent = "Må velge kjønn"
            return false
        }
        return true;
    }
}

function init() {
    const rootElement = document.getElementById("root")
    new DeltagerController(rootElement);
}
document.addEventListener("DOMContentLoaded", init)
