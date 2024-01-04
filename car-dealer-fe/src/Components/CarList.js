import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

import volvoImage from '../../src/Assets/volvo.png';

export default function Example() {
  const [data, setData] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  


  useEffect(() => {
    const token = localStorage.getItem('token');
    setIsLoggedIn(!!token);

    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/all');
        setData(response.data);
      } catch (error) {
        console.error('Error fetching car data:', error);
      }
    };

    fetchData();
  }, []);
  
  const handleDeleteClick = async (carId) => {
    const token = localStorage.getItem('token');
    if (carId === null) {
      return; // Exit the function if id is null
    }
    const apiUrl = 'http://localhost:8080/deleteCar?id='+carId;
    const requestOptions = {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      }
    };
    console.log(requestOptions)
    fetch(apiUrl, requestOptions).then(setData(data.filter(car => car.id !== carId)))
  };

  return (
    <>
      <div className="pt-24 pb-24">
        {data.map((car, index) => (
          <div key={index} className="flex justify-center mt-4 px-4 sm:px-0">
            <div className="w-full sm:w-11/12 md:w-9/12 h-auto rounded-lg bg-zinc-900 flex flex-col sm:flex-row justify-between car-list">
              <div className="flex items-center w-full sm:w-1/4 text-white m-2.5 overflow-hidden">
                <img
                  className="w-full max-w-full h-32 sm:h-auto object-contain object-center"
                  src={volvoImage}
                  alt="carImage"
                />
              </div>
              <div className="text-white w-full sm:w-7/12 m-0 py-2.5 flex flex-col justify-center items-center sm:items-start">
                <h2 className="text-2xl sm:text-3xl text-center sm:text-left">
                  {car.marka} {car.model} {car.nazwa} {car.rok}
                </h2>
                <p className="text-lg sm:text-xl text-center sm:text-left">
                  {car.przebieg} km, {car.pojemnosc} cm<sup>3</sup>, {car.moc} KM {car.paliwo}
                </p>
                <p className="text-lg sm:text-xl text-center sm:text-left">
                  {car.opis}
                </p>
              </div>
              <div className="text-white w-full sm:w-56 flex justify-start sm:float-right py-2.5 flex-col pr-2">
                <p className="text-3xl py-0.5 text-center sm:text-right">
                  {car.cena} PLN
                </p>
                {isLoggedIn ? (
                  <>
                    <Link to={`/EditAnnouncement/${car.id}`} className="text-md py-0.5 text-center sm:text-right hover:text-red-500">EDYTUJ OGŁOSZENIE</Link>
                    <button className="text-md py-0.5 text-center sm:text-right hover:text-red-500"
                    onClick={() => handleDeleteClick(car.id)}
                    >
                      USUŃ OGŁOSZENIE
                    </button>
                </>
                ) : (<p></p>)}
              </div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
}
