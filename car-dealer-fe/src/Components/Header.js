import React, { useState, useEffect } from 'react';
import { Dialog } from '@headlessui/react';
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline';
import logo from '../../src/Assets/Logo.svg';
import { Link } from 'react-router-dom';
import CarList from './CarList';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const navigation = [
  { name: 'Dodaj ogłoszenie', to: '/AddAnnouncement' },
];

const Header = () => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loggedInUserEmail, setLoggedInUserEmail] = useState('');

  useEffect(() => {
    const token = localStorage.getItem('token');
    setIsLoggedIn(!!token);
    const storedEmail = localStorage.getItem('loggedInUserEmail');
    setLoggedInUserEmail(storedEmail);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('loggedInUserEmail');
    setIsLoggedIn(false);
    setLoggedInUserEmail('');
    window.location.reload();
  };

  useEffect(() => {
    if (loggedInUserEmail) {
      toast.success(`Witaj ${loggedInUserEmail}`);
    }
  }, [loggedInUserEmail]);

  return (
    <div className="container mr-auto ml-auto">
      <header className="absolute inset-x-0 top-0 z-50">
        <nav className="mx-auto flex max-w-7xl items-center justify-between p-6 lg:px-8" aria-label="Global">
          <div className="flex lg:flex-1">
            <a href="/#" className="-m-1.5 p-1.5">
              <img className="h-8 w-auto fill-white" src={logo} alt="logo"></img>
            </a>
          </div>
          <div className="flex lg:hidden">
            <button
              type="button"
              className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-slate-200"
              onClick={() => setMobileMenuOpen(true)}
            >
              <span className="sr-only">Open main menu</span>
              <Bars3Icon className="h-6 w-6" aria-hidden="true" />
            </button>
          </div>
          <div className="hidden lg:flex lg:gap-x-12">
          {isLoggedIn ? (
            navigation.map((item) => (
              <Link key={item.name} to={item.to} className="text-sm font-semibold leading-6 text-slate-200 hover:text-red-500">
                {item.name}
              </Link>
            ))
            ) : (
              <div className="flex flex-col items-stretch">
              <p className="text-md grow font-semibold leading-6 text-slate-200 text-center"> Zapraszamy do kontaktu lub odwiedzenia nas stacjonarnie </p>
              <p className="text-md grow font-semibold leading-6 text-slate-200 text-center"> Krosno ul. Krakowska 11 tel. 667 432 123</p>
              </div>
            ) }
          </div>
          <div className="hidden lg:flex lg:flex-1 lg:justify-end">
            {isLoggedIn ? (
              <>
                <Link to="/register" className="text-sm font-semibold leading-6 pr-2 text-slate-200 hover:text-red-500">
                  Zarejestruj nowego użytkowanika
                </Link>
                <button
                  onClick={handleLogout}
                  className="text-sm font-semibold leading-6 text-slate-200 pr-2 hover:text-red-500"
                >
                  Wyloguj
                </button>
              </>
            ) : (
              <>
                <Link to="/login" className="text-sm font-semibold leading-6 text-slate-200 pr-2 hover:text-red-500">
                  Zaloguj
                </Link>
                
              </>
            )}
          </div>
        </nav>
        <Dialog as="div" className="lg:hidden" open={mobileMenuOpen} onClose={setMobileMenuOpen}>
          <div className="fixed inset-0 z-50" />
          <Dialog.Panel className="fixed inset-y-0 right-0 z-50 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
          </Dialog.Panel>
        </Dialog>
      </header>
      <CarList />
      <ToastContainer />
    </div>
  );
};

export default Header;
