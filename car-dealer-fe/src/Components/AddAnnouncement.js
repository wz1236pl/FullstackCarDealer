import React from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom"

import logo2 from '../Assets/Logo.svg'

export default function Example() {
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const token = localStorage.getItem('token');
    const headers = {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/x-www-form-urlencoded' // Use 'application/x-www-form-urlencoded' content type
    };

    // Serialize the form data
    const formData = new FormData(event.target);
    const formObject = {};
    formData.forEach((value, key) => {
      formObject[key] = value;
    });
    const serializedData = Object.entries(formObject)
      .map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`)
      .join('&');

    try {
      const response = await axios.post('http://localhost:8080/addCar', serializedData, {
        headers: headers
      });
      // Handle response
      console.log(response.status);
      navigate('/home'); // Navigate to '/home' after form submission
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <div className="flex min-h-screen items-center justify-center py-12 px-4 sm:px-6 lg:px-8" >
        <div className="w-full max-w-md space-y-8">
          <div>

            <Link to="/home"><img className="mx-auto h-12 w-auto" src={logo2} alt="car-dealer" /></Link>

          </div>
          <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
            <input type="hidden" name="remember" defaultValue="true" />
            <div className="-space-y-px rounded-md ">
              <div className=''>
                <label htmlFor="marka" >
                  Marka
                </label>
                <input
                  id="marka"
                  name="marka"
                  type="text"
                  autoComplete="marka"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Przebieg pojazdu"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="model" >
                  Model
                </label>
                <input
                  id="model"
                  name="model"
                  type="text"
                  autoComplete="model"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6 
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Model pojazdu"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="kolor" >
                  Kolor
                </label>
                <input
                  id="kolor"
                  name="kolor"
                  type="text"
                  autoComplete="kolor"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Przebieg pojazdu"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="typ" >
                  Typ
                </label>
                <select className="relative block w-full rounded-lg border-0 py-3  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" name="typ">
                  <option value="wybierz">Wybierz</option>
                  <option value="miejskie">Miejskie</option>
                  <option value="coupe">Coupe</option>
                  <option value="kabriolet">Kabriolet</option>
                  <option value="kombi">Kombi</option>
                  <option value="van">Van</option>
                  <option value="suv">Suv</option>
                  <option value="sedan">Sedan</option>
                </select>
              </div>

              <div className='pt-8'>
                <label htmlFor="paliwo" >
                  Paliwo
                </label>
                <select className="relative block w-full rounded-lg border-0 py-3  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" name="paliwo">
                  <option value="wybierz">Wybierz</option>
                  <option value="lpg+benzyna">Lpg + benzyna</option>
                  <option value="benzyna">Benzyna</option>
                  <option value="diesel">Diesel</option>
                </select>
              </div>

              <div className='pt-8'>
                <label htmlFor="rok" >
                  Rok
                </label>
                <input
                  id="rok"
                  name="rok"
                  type="text"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                  pattern="[0-9]{4}"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="przebieg" >
                  Przebieg
                </label>
                <input
                  id="przebieg"
                  name="przebieg"
                  type="text"
                  autoComplete="przebieg"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Przebieg pojazdu"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="moc" >
                  Moc
                </label>
                <input
                  id="moc"
                  name="moc"
                  type="text"
                  autoComplete="moc"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Np. 150"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="pojemnosc" >
                  Pojemność skokowa
                </label>
                <input
                  id="pojemnosc"
                  name="pojemnosc"
                  type="text"
                  autoComplete="pojemnosc"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Np. 1395"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="cena" >
                  Cena
                </label>
                <input
                  id="cena"
                  name="cena"
                  type="text"
                  autoComplete="cena"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Np. 8000"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="vin" >
                  Numer VIN
                </label>
                <input
                  id="vin"
                  name="vin"
                  type="text"
                  autoComplete="vin"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Np. TMBPH16Y633704492"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="nrRej" >
                  Numer Rejestracyjny
                </label>
                <input
                  id="nrRej"
                  name="nrRej"
                  type="text"
                  autoComplete="nrRej"
                  required
                  className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6
                  placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Np. RKR 1221A"
                />
              </div>

              <div className='pt-8'>
                <label htmlFor="opis" >
                  Opis
                </label>
                <textarea className="relative block w-full rounded-lg border-0 py-1.5  text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6 
                  placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="Opis samochodu" name="opis" rows="10" cols="50"></textarea>


              </div>
            </div>


            <div>
              <button
                type="submit"
                className="group relative flex w-38 justify-center mx-auto rounded-md bg-indigo-600 py-2 px-7 text-sm font-semibold text-white hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >

                Dodaj Ogłoszenie
              </button>
            </div>
          </form>
        </div>
      </div>
    </>
  )
}