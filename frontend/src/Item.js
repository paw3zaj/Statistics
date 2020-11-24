import React, {useEffect, useRef} from "react";
import { useTable } from 'react-table'
import { SERVER_URL } from './constant'
import axios from 'axios'

function Table({ columns, data }) {
    // Use the state and functions returned from useTable to build your UI
    const {
      getTableProps,
      getTableBodyProps,
      headerGroups,
      rows,
      prepareRow,
    } = useTable({
      columns,
      data,
    })
  
    // Render the UI for your table
    return (
      <table {...getTableProps()}>
        <thead>
          {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps()}>{column.render('Header')}</th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.map((row, i) => {
            prepareRow(row)
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => {
                  return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                })}
              </tr>
            )
          })}
        </tbody>
      </table>
    )
  }

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

    const handleInput = () => {

      (async function fetchData() {
        const response = await axios.get(url + '/findVirtuaByBarcode?barcode='+inputRef.current.value);
        setData(response.data);
      })();
      // if fetchData == null then fetch by sygnature

      (async function fetchHdata() {
        const response = await axios.get(url + '/findVirtuaLogsByBarcode?barcode='+inputRef.current.value);
        setHdata(response.data);
      })();

      inputRef.current.focus();
      inputRef.current.value="";
    };

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
        <h2>Dane aktualne</h2>
        <table>
          <tbody>
  <tr>
    <th>Virtua id:</th>
    <td>{data.idVirtua}</td>
  </tr>
  <tr>
    <th>Sygnatura:</th>
    <td>{data.signature}</td>
  </tr>
  <tr>
    <th>Kod kreskowy:</th>
    <td>{data.barCode}</td>
  </tr>
  <tr>
    <th>Autor:</th>
    <td>{data.author}</td>
  </tr>
  <tr>
    <th>Tytuł:</th>
    <td>{data.title}</td>
  </tr>
  <tr>
    <th>Status:</th>
    <td>{data.status}</td>
  </tr>
  <tr>
    <th>Data dodania egzemplarza:</th>
    <td>{data.createdDate}</td>
  </tr>
  </tbody>
</table>

<h2>Liczba wypożyczeń(...w budowie)</h2>

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