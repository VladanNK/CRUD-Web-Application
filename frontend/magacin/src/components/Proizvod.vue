<template>
  <div class="glavni">
    <h1>asdfasfafadfsasfasdfasdf</h1>
  </div>
</template>

<style>
body,
html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.glavni {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 20px;
  text-align: center;
  font-family: Georgia, 'Times New Roman', Times, serif;
}

.inputs {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 1200px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.radio-buttons {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-bottom: 20px;
}

.labela input[type='radio'] {
  transform: scale(1.5);
  margin-right: 10px;
}

.form-container {
  display: flex;
  justify-content: center;
  width: 100%;
  gap: 20px;
  flex-grow: 1;
}

.form-column {
  display: flex;
  flex-direction: column;
  flex: 1;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
}

label {
  margin-top: 10px;
}

input,
textarea {
  margin-top: 5px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 100%;
}

button {
  margin-top: 20px;
  padding: 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

button:hover {
  background-color: #45a049;
  transform: scale(1.05);
}

.tabela {
  width: 100%;
  max-width: 1200px;
  border-collapse: collapse;
  margin-top: 20px;
  background-color: #fff;
  border-radius: 5px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.tabela th,
.tabela td {
  border: 1.5px solid blue;
  padding: 10px;
  text-align: left;
}
.empty-cell {
  background-color: firebrick;
}
.non-empty-cell {
  background-color: steelblue;
}

.tabela th {
  background-color: #4caf50;
  color: white;
  font-weight: bold;
}

.popup {
  width: 400px;
  background: #fff;
  border-radius: 6px;
  position: absolute;
  top: 50%;
  left: 50%;
  text-align: center;
  padding: 0 30px 30px;
  color: #333;
  visibility: hidden;
}
.popup h2 {
  font-size: 38px;
  font-weight: 500;
  margin: 30px 0 10px;
}
.popup button {
  width: 100%;
  margin-top: 50px;
  padding: 10px 0;
  background: #6fd649;
  color: #fff;
  border: 0;
  outline: none;
  font-size: 18px;
  border-radius: 4px;
  cursor: pointer;
}

.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

  .dialog {
    background: white;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 300px;
    text-align: center;
}
  .dialog button {
    margin: 10px;
}
</style>

<script>
import axios from 'axios'

export default {
  name: 'Proizvod',
  data() {
    return {
      proizvodi: [],

      proizvod: {
        id: '',
        nazivProizvoda: '',
        cenaProizvoda: '',
        dostupnaKolicina: '',
        opis: '',
        idKategorije: ''
      }
    }
  },
  created() {
    this.ProizvodLoad()
  },
  methods: {
    ProizvodLoad() {
      axios
        .get('api_endpoint_url')
        .then((response) => {
          this.proizvodi = response.data
        })
        .catch((error) => {
          console.log(error)
        })
    },
    save() {
      if (this.proizvod.id) {
        this.updateData()
      } else {
        this.saveData()
      }
    },
    saveData() {
      axios
        .post('api_endpoint_url', this.proizvod)
        .then((response) => {
          this.resetForm()
          this.ProizvodLoad()
        })
        .catch((error) => {
          console.log(error)
        })
    },
    updateData() {
      axios
        .put(`api_endpoint_url/${this.proizvod.id}`, this.proizvod)
        .then((response) => {
          this.resetForm()
          this.ProizvodLoad()
        })
        .catch((error) => {
          console.log(error)
        })
    },
    resetForm() {
      this.proizvod = {
        id: '',
        nazivProizvoda: '',
        cenaProizvoda: '',
        dostupnaKolicina: '',
        opis: '',
        idKategorije: ''
      }
    },
    edit(proizvod) {
      this.proizvod = { ...proizvod }
    },
    deleteData() {
      axios
        .delete(`api_endpoint_url/${this.proizvod.id}`)
        .then((response) => {
          this.resetForm()
          this.ProizvodLoad()
        })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>
