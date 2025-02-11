<template>
  <div class="glavni">
    <h1 style="font-size: 40px; font-family: Georgia, 'Times New Roman', Times, serif">
      <u>Magacin</u>
    </h1>
    <p style="color: blue; font-size: 35px"><b>Izabrati neku od ponudjenih opcija:</b></p>
    <div class="radio-buttons">
      <div class="radio-button-holder">
        <input
          type="radio"
          id="kreiranje-kategorije"
          name="opcija"
          value="kreiranje"
          v-model="selectedOption"
          checked
        />
        <label class="labela" for="kreiranje-kategorije"> <b> Kreiranje kategorije </b> </label>
      </div>

      <div class="radio-button-holder">
        <input
          type="radio"
          id="unosProizvoda"
          name="opcija"
          value="dodavanje"
          v-model="selectedOption"
        />
        <label class="labela" for="unosProizvoda"> <b>Dodavanje proizvoda</b> </label>
      </div>
      <div class="radio-button-holder">
        <input
          type="radio"
          id="azuriranjeKategorije"
          name="opcija"
          value="azuriranjeKat"
          v-model="selectedOption"
        />
        <label class="labela" for="azuriranjeKategorije"> <b>Azuriranje kategorije</b> </label>
      </div>
      <div class="radio-button-holder">
        <input
          type="radio"
          id="azuriranjeProizvoda"
          name="opcija"
          value="azuriranje"
          v-model="selectedOption"
        />
        <label class="labela" for="azuriranjeProizvoda"> <b>Azuriranje proizvoda</b> </label>
      </div>
      <div class="radio-button-holder">
        <input
          type="radio"
          id="brisanjeKat"
          name="opcija"
          value="brisanjeKategorije"
          v-model="selectedOption"
        />
        <label class="labela" for="brisanjeKat"> <b>Brisanje kategorije</b> </label>
      </div>

      <div class="radio-button-holder">
        <input
          type="radio"
          id="brisanjeProizvoda"
          name="opcija"
          value="brisanje"
          v-model="selectedOption"
        />
        <label class="labela" for="brisanjeProizvoda"> <b>Brisanje proizvoda</b> </label>
      </div>
      <div class="radio-button-holder">
        <input
          type="radio"
          id="prikazSvega"
          name="opcija"
          value="prikazi"
          v-model="selectedOption"
        />
        <label class="labela" for="prikazSvega"> <b>Prikazi sve</b> </label>
      </div>
    </div>
    <h1 style="color: green"><u>Upravljanje Proizvodima</u></h1>
    <div class="inputs">
      <div class="form-container">
        <!-- Prva kolona za kreiranje kategorije -->
        <form
          @submit.prevent="validateFormZaDodavanjeKategorije"
          v-if="selectedOption === 'kreiranje'"
        >
          <div class="form-column" id="kreiranje_kategorije">
            <h2><i>Kreiranje kategorije</i></h2>
            <label for="nazivKategorije">Uneti naziv kategorije:</label>
            <input
              type="text"
              @change="printKategorija()"
              name="nazivKategorije"
              v-model="kategorija.nazivKategorije"
              id="nazivKategorije"
              placeholder="naziv kategorije"
            />

            <button
              type="submit"
              name="kreirajKategoriju"
              @click="validateFormZaDodavanjeKategorije"
              id="dodajKategoriju"
              style="border-radius: 30px"
            >
              Dodaj kategoriju
            </button>
          </div>
        </form>
        <!-- Dijalozi za uspesno kreiranje kategorije i za validaciju -->
        <div v-if="showDialog5" class="dialog-overlay" @click.self="showDialog5 = false">
          <div class="dialog" ref="dialog">
            <h2>Potvrda dodavanja kategorije</h2>
            <p>Uspesno dodata kategorija</p>
            <button @click="removeOdDijaloga5">OK</button>
          </div>
        </div>

        <div
          v-if="validateFormZaDodavanjeKat"
          class="dialog-overlay"
          @click.self="validateFormZaDodavanjeKat = false"
        >
          <div class="dialog" ref="dialog">
            <h2>Greska</h2>
            <p>Potrebno je uneti naziv kategorije!</p>
            <button @click="validateFormZaDodavanjeKat = false">OK</button>
          </div>
        </div>

        <form @submit.prevent="validateFormZaDodavanje" v-if="selectedOption === 'dodavanje'">
          <!-- Druga kolona dodavanje -->
          <div class="form-column" id="dodavanje_proizvoda">
            <h2><i>Dodavanje proizvoda</i></h2>
            <label for="nazivProizvoda">Uneti ime proizvoda:</label>
            <input
              type="text"
              name="nazivProizvoda"
              v-model="proizvod.nazivProizvoda"
              id="nazivProizvoda"
              placeholder="naziv proizvoda"
            />

            <label for="cenaProizvoda">Uneti cenu proizvoda:</label>
            <input
              type="number"
              name="cenaProizvoda"
              v-model="proizvod.cenaProizvoda"
              id="cenaProizvoda"
              placeholder="cena proizvoda"
            />

            <label for="kolicinaProizvoda">Uneti kolicinu proizvoda:</label>
            <input
              type="number"
              name="kolicinaProizvoda"
              v-model="proizvod.dostupnaKolicina"
              id="kolicinaProizvoda"
              placeholder="kolicina proizvoda"
            />

            <label for="opisProizvoda">Uneti opis proizvoda:</label>
            <textarea
              name="opisProizvoda"
              id="opisProizvoda"
              v-model="proizvod.opis"
              placeholder="opis..."
            ></textarea>

            <label for="kategorijaProizvoda"> Izaberite kategoriju proizvoda: </label>
            <select
              name="kategorijaProizvoda"
              id="kategorijaProizvoda"
              v-model="proizvod.kategorija"
            >
              <option
                v-for="kategorija in kategorije"
                :key="kategorija.idKategorije"
                :value="kategorija"
              >
                {{ kategorija.nazivKategorije }}
              </option>
            </select>

            <!--  <label for="kategorijaProizvoda">Uneti ID kategorije proizvoda:</label>
            <input
              type="number"
              name="kategorijaProizvoda"
              v-model="proizvod.idKategorije"
              id="kategorijaProizvoda"
              placeholder="kategorija proizvoda"
            /> -->
            <!-- validateFormZaDodavanje() -->
            <button
              type="submit"
              name="kreirajProizvod"
              style="border-radius: 30px"
              @click="validateFormZaDodavanje"
              id="dodajProizvod"
            >
              Dodaj proizvod
            </button>

            <!-- Dijalozi za uspesno kreiranje proizvoda i za validaciju -->
            <div v-if="showDialog6" class="dialog-overlay" @click.self="showDialog6 = false">
              <div class="dialog" ref="dialog">
                <h2>Potvrda dodavanja proizvoda</h2>
                <p>Uspesno dodat proizvod</p>
                <button @click="removeOdDijaloga6">OK</button>
              </div>
            </div>

            <div
              v-if="validateFormZaDodavanjePro"
              class="dialog-overlay"
              @click.self="validateFormZaDodavanjeKat = false"
            >
              <div class="dialog" ref="dialog">
                <h2>Greska</h2>
                <p>Potrebno je uneti sve podatke!</p>
                <button @click="validateFormZaDodavanjePro = false">OK</button>
              </div>
            </div>
          </div>
        </form>
        <!-- Treca kolona azuriranje -->
        <form @submit.prevent="confirmRemove4" v-if="selectedOption === 'azuriranjeKat'">
          <div class="form-column" id="azuriranje_kategorije">
            <h2><i>Azuriranje kategorije</i></h2>
            <label for="idKategorije">Uneti ID kategorije:</label>
            <input
              type="number"
              name="idKategorije"
              v-model="kategorija.idKategorije"
              id="idKategorije"
              readonly
              placeholder="ID kategorije"
            />
            <label for="nazivKategorije">Uneti novo ime kategorije:</label>
            <input
              type="text"
              name="nazivKategorije"
              v-model="kategorija.nazivKategorije"
              id="nazivKategorije"
              placeholder="naziv kategorije"
            />
            <button
              name="azurirajKategoriju"
              @click="confirmRemove4"
              id="azurirajKategoriju"
              style="border-radius: 30px"
            >
              Azuriraj kategoriju
            </button>
          </div>
        </form>
        <!-- Dijalog za azuriranje kategorije i validacija podataka -->
        <div
          v-if="validateFormZaAzuriranjeKat"
          class="dialog-overlay"
          @click.self="validateFormZaAzuriranjeKat = false"
        >
          <div class="dialog" ref="dialog">
            <h2>Greska</h2>
            <p>Potrebno je uneti sve podatke!</p>
            <button @click="validateFormZaAzuriranjeKat = false">OK</button>
          </div>
        </div>
        <div v-if="showDialog4" class="dialog-overlay" @click.self="showDialog4 = false">
          <div class="dialog" ref="dialog">
            <h2>Potvrda azuriranja kategorije</h2>
            <p>
              Da li ste sigurni da želite da azurirate kategoriju sa ID-jem:
              {{ kategorija.idKategorije }}?
            </p>
            <button @click="removeOdDijaloga4">Da</button>
            <button @click="showDialog4 = false">Ne</button>
          </div>
        </div>

        <form @submit.prevent="confirmRemove3" v-if="selectedOption === 'azuriranje'">
          <div class="form-column" id="azuriranje_proizvoda">
            <h2><i>Azuriranje proizvoda</i></h2>
            <label for="idProizvoda">Uneti ID proizvoda:</label>
            <input
              type="number"
              name="idProizvoda"
              v-model="proizvod.idProizvoda"
              id="idProizvoda"
              readonly
              placeholder="ID proizvoda"
            />

            <label for="nazivProizvoda2">Uneti novo ime proizvoda:</label>
            <input
              type="text"
              name="nazivProizvoda2"
              v-model="proizvod.nazivProizvoda"
              id="nazivProizvoda2"
              placeholder="naziv proizvoda"
            />

            <label for="cenaProizvoda2">Uneti novu cenu proizvoda:</label>
            <input
              type="number"
              name="cenaProizvoda2"
              v-model="proizvod.cenaProizvoda"
              id="cenaProizvoda2"
              placeholder="cena proizvoda"
            />

            <label for="kolicinaProizvoda2">Uneti novu kolicinu proizvoda:</label>
            <input
              type="number"
              name="kolicinaProizvoda2"
              v-model="proizvod.dostupnaKolicina"
              id="kolicinaProizvoda2"
              placeholder="kolicina proizvoda"
            />

            <label for="opisProizvoda2">Uneti novi opis proizvoda:</label>
            <textarea
              name="opisProizvoda2"
              id="opisProizvoda2"
              v-model="proizvod.opis"
              placeholder="opis..."
            ></textarea>

            <label for="kategorijaProizvoda"> Izaberite kategoriju proizvoda: </label>
            <select
              name="kategorijaProizvoda"
              id="kategorijaProizvoda"
              v-model="proizvod.kategorija"
              @change="updateNazivKategorije"
            >
              <option
                v-for="kategorija in kategorije"
                :key="kategorija.idKategorije"
                :value="kategorija"
              >
                Naziv: {{ kategorija.nazivKategorije }}
              </option>
            </select>

            <!-- <label for="kategorijaProizvoda2">Uneti novu ID kategoriju proizvoda:</label>
            <input
              type="number"
              name="kategorijaProizvoda2"
              id="kategorijaProizvoda2"
              v-model="proizvod.idKategorije"
              placeholder="kategorija proizvoda"
            /> -->

            <!--<label for="imeKategorije2">Uneti naziv kategorije</label>
                        <input type="text" name="imeKategorije2" id="imeKategorije2" v-model="proizvod.kategorija" placeholder="Novi naziv kategorije">-->

            <button
              name="azurirajProizvod"
              @click="confirmRemove3"
              id="azurirajProizvod"
              style="border-radius: 30px"
            >
              Azuriraj proizvod
            </button>
          </div>
        </form>
        <!-- Dijalog za azuriranje proizvoda i validacija podataka -->

        <div
          v-if="validateFormZaAzuriranjePro"
          class="dialog-overlay"
          @click.self="validateFormZaAzuriranjePro = false"
        >
          <div class="dialog" ref="dialog">
            <h2>Greska</h2>
            <p>Potrebno je uneti sve podatke!</p>
            <button @click="validateFormZaAzuriranjePro = false">OK</button>
          </div>
        </div>

        <div v-if="showDialog3" class="dialog-overlay" @click.self="showDialog3 = false">
          <div class="dialog" ref="dialog">
            <h2>Potvrda azuriranja proizvoda</h2>
            <p>
              Da li ste sigurni da želite da azurirate proizvod sa ID-jem:
              {{ proizvod.idProizvoda }}?
            </p>
            <button @click="removeOdDijaloga3">Da</button>
            <button @click="showDialog3 = false">Ne</button>
          </div>
        </div>

        <!-- Cetvrta kolona brisanje -->
        <form @submit.prevent="confirmRemove2" v-if="selectedOption === 'brisanjeKategorije'">
          <div class="form-column" id="brisanjeKat">
            <h2><i>Brisanje kategorije</i></h2>
            <label for="idKategorije">ID kategorije:</label>
            <input
              type="number"
              name="idKategorije"
              id="idKategorije"
              v-model="kategorija.idKategorije"
              readonly
              placeholder="ID kategorije"
            />
            <button
              name="obrisiKategoriju"
              @click="confirmRemove2"
              id="obrisiKategoriju"
              style="border-radius: 30px"
            >
              Obrisi kategoriju
            </button>
          </div>
        </form>
        <!-- dijalog za brisanje kategorije i validacija podataka -->
        <div
          v-if="validateFormZaBrisanjeKat"
          class="dialog-overlay"
          @click.self="validateFormZaBrisanjeKat = false"
        >
          <div class="dialog" ref="dialog">
            <h2>Greska</h2>
            <p>Potrebno je uneti ID kategorije!</p>
            <button @click="validateFormZaBrisanjeKat = false">OK</button>
          </div>
        </div>
        <div v-if="showDialog2" class="dialog-overlay" @click.self="showDialog2 = false">
          <div class="dialog" ref="dialog">
            <h2>Potvrda brisanja kategorije</h2>
            <p>
              Da li ste sigurni da želite da obrišete kategoriju sa ID-jem:
              {{ kategorija.idKategorije }}?
            </p>
            <button @click="removeOdDijaloga2">Da</button>
            <button @click="showDialog2 = false">Ne</button>
          </div>
        </div>
        <form @submit.prevent="confirmRemove" v-if="selectedOption === 'brisanje'">
          <div class="form-column" id="brisanje_proizvoda">
            <h2><i>Brisanje proizvoda</i></h2>
            <label for="idProizvoda">Uneti ID proizvoda:</label>
            <input
              type="number"
              name="idProizvoda"
              id="idProizvoda"
              v-model="proizvod.idProizvoda"
              placeholder="ID proizvoda"
            />
            <!-- @click="remove" -->
            <!-- remove -->
            <button
              name="obrisiProizvod"
              @click="confirmRemove"
              id="obrisiProizvod"
              style="border-radius: 30px"
            >
              Obrisi proizvod
            </button>
          </div>
        </form>
        <!-- Dijalog za brisanje proizvoda i validacija podataka -->

        <div
          v-if="validateFormZaBrisanjePro"
          class="dialog-overlay"
          @click.self="validateFormZaBrisanjePro = false"
        >
          <div class="dialog" ref="dialog">
            <h2>Greska</h2>
            <p>Potrebno je uneti ID proizvoda!</p>
            <button @click="validateFormZaBrisanjePro = false">OK</button>
          </div>
        </div>
        <div v-if="showDialog" class="dialog-overlay" @click.self="showDialog = false">
          <div class="dialog" ref="dialog">
            <h2>Potvrda brisanja proizvoda</h2>
            <p>
              Da li ste sigurni da želite da obrišete proizvod sa ID-jem:
              {{ proizvod.idProizvoda }}?
            </p>
            <button @click="removeOdDijaloga">Da</button>
            <button @click="showDialog = false">Ne</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Tabela kategorija -->
    <table
      class="tabela"
      id="tabela2"
      v-if="
        selectedOption === 'kreiranje' ||
        selectedOption === 'azuriranjeKat' ||
        selectedOption === 'brisanjeKategorije' ||
        selectedOption === 'prikazi'
      "
    >
      <thead>
        <tr>
          <th>ID Kategorije</th>
          <th>Naziv Kategorije</th>
        </tr>
      </thead>
      <tbody>
        <template v-if="kategorije == undefined || kategorije.length == 0">
          <tr>
            <td colspan="2">Ne postoji kategorija</td>
          </tr>
        </template>
        <template v-else>
          <tr
            v-for="kategorija in kategorije"
            v-bind:key="kategorija.idKategorije"
            @click="
              ;(selectedOption === 'azuriranjeKat' ? selectKategorija(kategorija) : '') ||
                (selectedOption === 'brisanjeKategorije' ? selectKategorija(kategorija) : '')
            "
          >
            <td :class="getClass(kategorija.idKategorije, true)">{{ kategorija.idKategorije }}</td>
            <td :class="getClass(kategorija.nazivKategorije)">{{ kategorija.nazivKategorije }}</td>
          </tr>
        </template>
      </tbody>
    </table>
    <!-- Tabela proizvoda -->
    <table
      class="tabela"
      id="tabela2"
      v-if="
        selectedOption === 'dodavanje' ||
        selectedOption === 'azuriranje' ||
        selectedOption === 'brisanje' ||
        selectedOption === 'prikazi'
      "
    >
      <thead>
        <tr>
          <th>ID Proizvoda</th>
          <th>Naziv proizvoda</th>
          <th>Cena proizvoda</th>
          <th>Dostupna kolicina</th>
          <th>Opis</th>
          <th>ID Kategorije</th>
          <th>Naziv Kategorije</th>
        </tr>
      </thead>
      <tbody>
        <template v-if="!proizvodi || proizvodi.length == 0">
          <tr>
            <td colspan="7">Trenutno nema nijednog prozivoda</td>
          </tr>
        </template>
        <template v-else>
          <tr
            v-for="proizvod in proizvodi"
            v-bind:key="proizvod.idProizvoda"
            @click="
              ;(selectedOption === 'azuriranje' ? selectProizvod(proizvod) : '') ||
                (selectedOption === 'brisanje' ? selectProizvod(proizvod) : '')
            "
          >
            <td :class="getClass(proizvod.idProizvoda, true)">{{ proizvod.idProizvoda }}</td>
            <td :class="getClass(proizvod.nazivProizvoda)">{{ proizvod.nazivProizvoda }}</td>
            <td :class="getClass(proizvod.cenaProizvoda, true)">{{ proizvod.cenaProizvoda }}</td>
            <td :class="getClass(proizvod.dostupnaKolicina, true)">
              {{ proizvod.dostupnaKolicina }}
            </td>
            <td :class="getClass(proizvod.opis)">{{ proizvod.opis }}</td>
            <td
              v-if="
                proizvod.kategorija !== null &&
                proizvod.kategorija.idKategorije != null &&
                proizvod.kategorija.idKategorije != undefined
              "
              :class="getClass(proizvod.kategorija.idKategorije, true)"
            >
              {{ proizvod.kategorija.idKategorije }}
            </td>
            <td
              v-if="
                proizvod.kategorija !== null &&
                proizvod.kategorija.nazivKategorije != null &&
                proizvod.kategorija.nazivKategorije != undefined
              "
              :class="getClass(proizvod.kategorija.nazivKategorije)"
            >
              {{ proizvod.kategorija.nazivKategorije }}
            </td>
          </tr>
        </template>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Proizvod',
  data() {
    return {
      proizvodi: [],
      kategorije: [],
      kategorija: {
        idKategorije: '',
        nazivKategorije: ''
      },
      proizvod: {
        idProizvoda: '',
        nazivProizvoda: '',
        cenaProizvoda: '',
        dostupnaKolicina: '',
        opis: '',
        idKategorije: '',
        nazivKategorije: '',
        kategorija: undefined
      },
      isSuccess: false,
      nadjen: false,
      selectedOption: 'kreiranje',
      showDialog: false,
      showDialog2: false,
      showDialog3: false,
      showDialog4: false,
      showDialog5: false,
      showDialog6: false,
      validateFormZaDodavanjeKat: false,
      validateFormZaDodavanjePro: false,
      validateFormZaAzuriranjeKat: false,
      validateFormZaAzuriranjePro: false,
      validateFormZaBrisanjeKat: false,
      validateFormZaBrisanjePro: false
    }
  },
  created() {
    this.loadProizvodi()
    this.loadKategorije()
  },
  methods: {
    confirmRemove() {
      if (!this.proizvod.idProizvoda) {
        this.validateFormZaBrisanjePro = true
        this.$nextTick(() => {
          this.$refs.dialog.focus()
        })
      } else {
        this.isSuccess = true
        this.showDialog = true
      }
    },
    confirmRemove2() {
      if (!this.kategorija.idKategorije || !this.kategorija.nazivKategorije) {
        this.nadjen = false
        this.validateFormZaBrisanjeKat = true
        this.$nextTick(() => {
          this.$refs.dialog.focus()
        })
      } else {
        this.nadjen = true
        this.showDialog2 = true
      }
    },
    confirmRemove3() {
      if (
        !this.proizvod.nazivProizvoda ||
        !this.proizvod.cenaProizvoda ||
        !this.proizvod.dostupnaKolicina ||
        !this.proizvod.opis ||
        !this.proizvod.kategorija.idKategorije
      ) {
        this.validateFormZaAzuriranjePro = true
        this.$nextTick(() => {
          this.$refs.dialog.focus()
        })
      } else {
        this.isSuccess = true
        this.showDialog3 = true
      }
    },
    confirmRemove4() {
      if (!this.kategorija.nazivKategorije) {
        this.validateFormZaAzuriranjeKat = true
        this.$nextTick(() => {
          this.$refs.dialog.focus()
        })
      } else {
        this.isSuccess = true
        this.showDialog4 = true
      }
    },
    removeOdDijaloga() {
      this.remove()
      this.showDialog = false
    },
    removeOdDijaloga2() {
      this.remove2()
      this.showDialog2 = false
    },
    removeOdDijaloga3() {
      this.update()
      this.showDialog3 = false
    },
    removeOdDijaloga4() {
      this.updateKategorija()
      this.showDialog4 = false
    },
    removeOdDijaloga5() {
      this.saveKategorija()
      this.showDialog5 = false
    },
    removeOdDijaloga6() {
      this.save()
      this.showDialog6 = false
    },
    getClass(content, isNumeric = false) {
      if (isNumeric) {
        return content === undefined ||
          content === null ||
          String(content).trim() === '' ||
          isNaN(content)
          ? 'empty-cell'
          : 'non-empty-cell'
      }
      return content === undefined || content === null || String(content).trim() === ''
        ? 'empty-cell'
        : 'non-empty-cell'
    },
    printKategorija() {
      console.log(this.kategorija)
      console.log('Promena')
    },
    selectProizvod(proizvod) {
      this.proizvod = { ...proizvod }
    },
    selectKategorija(kategorija) {
      this.kategorija = { ...kategorija }
    },
    validateFormZaDodavanjeKategorije() {
      if (!this.kategorija.nazivKategorije) {
        this.validateFormZaDodavanjeKat = true
        this.$nextTick(() => {
          this.$refs.dialog.focus()
        })
      } else {
        this.isSuccess = true
        this.showDialog5 = true
      }
    },
    validateFormZaDodavanje() {
      if (
        !this.proizvod.nazivProizvoda ||
        !this.proizvod.cenaProizvoda ||
        !this.proizvod.dostupnaKolicina ||
        !this.proizvod.opis
      ) {
        this.validateFormZaDodavanjePro = true
        this.$nextTick(() => {
          this.$refs.dialog.focus()
        })
      } else {
        this.isSuccess = true
        this.showDialog6 = true
      }
    },

    dohvatiKategorije() {
      fetch('http://localhost:8080/kategorije')
        .then((response) => response.json())
        .then((data) => {
          this.kategorije = data
        })
        .catch((error) => console.error('Greska:', error))
    },
    addKategorija(novaKategorija) {
      this.kategorije.push(novaKategorija)
    },

    loadProizvodi() {
      axios
        .get('http://localhost:8080/proizvodi')
        .then(({ data }) => {
          this.proizvodi = data.map((proizvod) => {
            const kategorija = this.kategorije.find((k) => k.idKategorije === proizvod.idKategorije)
            if (kategorija) {
              proizvod.nazivKategorije = kategorija.nazivKategorije
              proizvod.kategorija = kategorija
            }
            return proizvod
          })
        })
        .catch((error) => {
          console.error('Greška pri učitavanju proizvoda:', error)
        })
    },
    loadKategorije() {
      axios
        .get('http://localhost:8080/kategorije')
        .then(({ data }) => {
          this.kategorije = data
          this.loadProizvodi()
        })
        .catch((error) => {
          console.error('Greška pri učitavanju kategorija:', error)
        })
    },
    updateKategorija() {
      if (!this.kategorija.idKategorije || isNaN(parseInt(this.kategorija.idKategorije))) {
        return
      }
      const id = parseInt(this.kategorija.idKategorije)

      const index_kategorije = this.kategorije.findIndex(
        (kategorije) => this.kategorija.idKategorije === id
      )

      if (index_kategorije !== -1) {
        axios
          .put(`http://localhost:8080/kategorijaUpdate/${id}`, this.kategorija)
          .then(({ data }) => {
            this.loadKategorije()
            Object.assign(this.kategorije[index_kategorije], data.kategorija)
            this.kategorija.idKategorije = ''
            this.kategorija.nazivKategorije = ''
            //alert('Kategorija je uspešno ažurirana')
          })
          .catch((error) => {
            console.error('Greška pri ažuriranju kategorije', error)
          })
      } else {
        axios
          .post('http://localhost:8080/newKategorija', this.kategorija)
          .then(({ data }) => {
            this.loadKategorije()
            this.kategorije.push(data.kategorija)
            alert('Kategorija je kreirana')
          })
          .catch((error) => {
            console.error('Greska pri kreiranju kategorije', error)
          })
      }
    },
    saveKategorija() {
      axios
        .post('http://localhost:8080/newKategorija', this.kategorija)
        .then(() => {
          this.loadKategorije()
          this.kategorija.nazivKategorije = ''
        })
        .catch((error) => {
          console.error('Greska pri dodavanju kategorije', error)
        })
    },
    save() {
      axios
        .post('http://localhost:8080/newProizvod', {
          nazivProizvoda: this.proizvod.nazivProizvoda,
          cenaProizvoda: this.proizvod.cenaProizvoda,
          dostupnaKolicina: this.proizvod.dostupnaKolicina,
          opis: this.proizvod.opis,
          idKategorije: this.proizvod.kategorija.idKategorije,
          nazivKategorije: this.proizvod.kategorija.nazivKategorije,
          kategorija: this.proizvod.kategorija
        })
        .then(() => {
          this.loadProizvodi()
          this.proizvod.nazivProizvoda = ''
          this.proizvod.cenaProizvoda = ''
          this.proizvod.dostupnaKolicina = ''
          this.proizvod.opis = ''
          this.proizvod.idKategorije = ''
          this.proizvod.nazivKategorije = ''
        })
        .catch((error) => {
          console.error('Greška pri čuvanju proizvoda:', error)
        })
    },
    update() {
      if (!this.proizvod.idProizvoda || isNaN(parseInt(this.proizvod.idProizvoda))) {
        console.log(this.proizvod.idProizvoda)
        alert('Neispravan format')
        return
      }

      const id = parseInt(this.proizvod.idProizvoda)

      const index_proizvoda = this.proizvodi.findIndex((proizvod) => proizvod.idProizvoda === id)

      if (index_proizvoda !== -1) {
        axios
          .put(`http://localhost:8080/proizvodUpdate/${id}`, this.proizvod)
          .then(({ data }) => {
            this.loadProizvodi()
            Object.assign(this.proizvodi[index_proizvoda], data.proizvod)
            console.log(this.proizvod)
            this.proizvod.idProizvoda = ''
            this.proizvod.nazivProizvoda = ''
            this.proizvod.cenaProizvoda = ''
            this.proizvod.dostupnaKolicina = ''
            this.proizvod.opis = ''
            this.proizvod.idKategorije = ''
            this.proizvod.nazivKategorije = ''
            //this.proizvod.kategorija = undefined
          })
          .catch((error) => {
            console.error('Greška pri ažuriranju proizvoda', error)
          })
      } else {
        axios
          .post('http://localhost:8080/newProizvod', this.proizvod)
          .then(({ data }) => {
            this.loadProizvodi()
            this.proizvodi.push(data.proizvod)
            alert('Proizvod je kreiran')
          })
          .catch((error) => {
            console.error('Greska pri kreiranju proizvoda', error)
          })
      }
    },
    remove2() {
      if (!this.kategorija.idKategorije || isNaN(parseInt(this.kategorija.idKategorije))) {
        alert('Neispravan format')
        return
      }
      const id = parseInt(this.kategorija.idKategorije)

      const index_kategorije_delete = this.kategorije.findIndex(
        (kategorija) => kategorija.idKategorije === id
      )

      if (index_kategorije_delete !== -1) {
        axios
          .delete(`http://localhost:8080/deleteKategorija2/${id}`) //ispravi posle
          .then(() => {
            this.kategorije.splice(index_kategorije_delete, 1)
            this.loadKategorije()
            this.kategorija.idKategorije = ''
          })
          .catch((error) => {
            console.error('Greska pri brisanju kategorije', error)
          })
      } else {
        alert('Kategorija ne postoji')
      }
    },

    remove() {
      if (!this.proizvod.idProizvoda || isNaN(parseInt(this.proizvod.idProizvoda))) {
        console.log(this.proizvod.idProizvoda)
        return
      } else {
        console.log(this.proizvod.idProizvoda)
      }

      const id = parseInt(this.proizvod.idProizvoda)

      const index_proizvoda_delete = this.proizvodi.findIndex(
        (proizvod) => proizvod.idProizvoda === id
      )

      if (index_proizvoda_delete !== -1) {
        axios
          .delete(`http://localhost:8080/proizvodDelete/${id}`)
          .then(() => {
            this.proizvodi.splice(index_proizvoda_delete, 1)
            this.loadProizvodi()
            this.proizvod.idProizvoda = ''
          })
          .catch((error) => {
            console.error('Greska pri brisanju proizvoda', error)
          })
      } else {
        alert('Proizvod ne postoji')
      }
    }
  },
  watch: {
    /*'proizvod.idKategorije': function (newId) {
      const kategorija = this.kategorije.find((k) => k.idKategorije === newId)
      if (kategorija) {
        this.proizvod.nazivKategorije = kategorija.nazivKategorije
      } else {
        this.proizvod.nazivKategorije = ''
      }
    },*/
    'kategorija.nazivKategorije': function (newNaziv) {
      if (newNaziv) {
        this.proizvod.nazivKategorije = newNaziv.nazivKategorije
        console.log(this.proizvod.nazivKategorije)
      }
    },
    'proizvod.kategorija': function (newKategorija) {
      if (newKategorija) {
        this.proizvod.idKategorije = newKategorija.idKategorije
        this.proizvod.nazivKategorije = newKategorija.nazivKategorije
      } else {
        this.proizvod.idKategorije = ''
        this.proizvod.nazivKategorije = ''
      }
    }
  },
  mounted() {
    this.dohvatiKategorije()
  }
}
</script>

<style scoped>
.radio-button-holder {
  display: flex;
  gap: 10px;
  padding-bottom: 4px;
}
.radio-button-holder:last-child {
  padding-bottom: 0px;
}

.radio-button-holder input {
  width: fit-content;
}
</style>