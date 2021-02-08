import React, {useEffect, useRef} from "react";
import Table from "./Table"
import { SERVER_URL } from './constant'
import axios from 'axios'

const Item = ({
  data,
  setData,
  hdata,
  setHdata
  }) => {

  const inputRef = useRef(null);
  const url = SERVER_URL; 
  
    useEffect(() => {
      inputRef.current.focus();
    }, []);

    async function dataFetch() {

        console.log('początek');

        const barcode = inputRef.current.value;

        console.log(barcode);

        await axios.get(url + '/getVirtuaByBarcode?barcode='+barcode)
            .then(response => {setData(response.data)});
        await axios.get(`${url}/getVirtuaLogsByBarcode?barcode=104618751000`) //${inputRef.current.value}`)
            .then(response => {setHdata(response.data)});
        console.log('przed');
        console.log(data.title);
        console.log('po');
    }

    const handleInput = () => {


        console.log(inputRef.current.value);

      // (async function fetchData() {
      //   const response = await axios.get(url + '/getVirtuaByBarcode?barcode='+inputRef.current.value);
      //   setData(response.data);
      // })();
      // // if fetchData == null then fetch by sygnature
      //
      // (async function fetchHdata() {
      //   const response = await axios.get(url + '/getVirtuaLogsByBarcode?barcode='+inputRef.current.value);
      //   setHdata(response.data);
      //
      // })();

      inputRef.current.focus();
      inputRef.current.value="";

      return dataFetch();
    };

    const columnsC = React.useMemo(
      () => [
          {
            Header: 'Virtua id',
            accessor: 'data.signature'
          },
          {
            Header: 'Sygnatura',
            accessor: 'signature'
          },
          {
            Header: 'Kod kreskowy',
            accessor: 'barcode'
          },
          {
            Header: 'Autor',
            accessor: 'author'
          },
          {
            Header: 'Tytół',
            accessor: 'title'
          },
          {
            Header: 'Status',
            accessor: 'status'
          },
          {
            Header: 'Data dodania egzemplarza',
            accessor: 'createdDate'
          }
        ],
        []
      )

    const columnsH = React.useMemo(
        () => [
            {
              Header: 'Data zmiany',
              accessor: 'createdDate'
            },
            {
              Header: 'Sygnatura',
              accessor: 'signature'
            },
            {
              Header: 'Kod kreskowy',
              accessor: 'barcode'
            },
            {
              Header: 'Autor',
              accessor: 'data.author'
            },
            {
              Header: 'Tytół',
              accessor: 'title.title'
            },
            {
              Header: 'Status',
              accessor: 'status'
            }
          ],
          []
        )

        const columnsB = React.useMemo(
            () => [
              {
                Header: '',
                accessor: 'createdDate',
              },
              {
                Header: 'Styczeń',
                accessor: 'virtua.signature'
              },
              {
                Header: 'Luty',
                accessor: 'virtua.barcode'
              },
              {
                Header: 'Marzec',
                accessor: 'virtua.author',
              },
              {
                Header: 'Kwiecień',
                accessor: 'virtua.title'
              },
              {
                Header: 'Maj',
                accessor: ''
              },
              {
                Header: 'Czerwiec',
                accessor: ''
              },
              {
                Header: 'Lipiec',
                accessor: ''
              },
              {
                Header: 'Sierpień',
                accessor: ''
              },
              {
                Header: 'Wrzesień',
                accessor: ''
              },
              {
                Header: 'Padziernik',
                accessor: ''
              },
              {
                Header: 'Listopad',
                accessor: ''
              },
              {
                Header: 'Grudzień',
                accessor: '',
              },
              {
                Header: 'Suma roczna',
                accessor: ''
              },
              {
                Header: 'Suma całkowita',
                accessor: ''
              }
            ],
            []
          )

  return (
    <div>
        <input type="text" ref={inputRef} placeholder="wpisz kod kreskowy" />
        <button onClick={handleInput}>Generuj</button>
        <hr />
        <h2>Dane zasobu</h2>
      
      <Table
      columns={columnsC}
      data={data}
      />

<h2>Liczba wypożyczeń</h2>

      <Table
     columns={columnsB}
     data={hdata}
   />

<h2>Dane historyczne</h2>
     <Table
     columns={columnsH}
     data={hdata}
    //  setData={setHdata}
   />

    </div>

  );
}

export default Item