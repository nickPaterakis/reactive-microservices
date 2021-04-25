import React, { useRef } from 'react';
import PropTypes from 'prop-types';
import { BsPlus } from 'react-icons/bs';
import { RiSubtractFill } from 'react-icons/ri';
import useOutsideClick from '../../hooks/useOutsideClick';
import Aux from '../../hoc/Auxiliary';

const GuestsDropdown = ({
  open,
  removeAdult,
  addAdult,
  adults,
  handleOpen,
  removeChild,
  addChild,
  childrenNumber,
  removeRoom,
  addRoom,
  rooms,
}) => {
  const wrapperRef = useRef(null);
  useOutsideClick(wrapperRef, handleOpen);

  return (
    <Aux>
      {open ? (
        <div className="dropdown dropdown--guests" ref={open ? wrapperRef : null}>
          <div className="dropdown__item dropdown__item--guests">
            <span className="dropdown__item__text">Adoults</span>
            <div className="dropdown__item__buttons">
              <button type="button" className={`btn btn--guests ${adults > 0 ? 'btn--white' : 'btn--disabled'}`} aria-label="remove-adult" onClick={removeAdult}><RiSubtractFill /></button>
              <span className="dropdown__item__number">{adults}</span>
              <button type="button" className="btn btn--guests btn--white" aria-label="add-adult" onClick={addAdult}><BsPlus /></button>
            </div>
          </div>
          <div className="dropdown__item dropdown__item--guests">
            <span className="dropdown__item__text">Children</span>
            <div className="dropdown__item__buttons">
              <button type="button" className={`btn btn--guests ${childrenNumber > 0 ? 'btn--white' : 'btn--disabled'}`} aria-label="remove-adult" onClick={removeChild}><RiSubtractFill /></button>
              <span className="dropdown__item__number">{childrenNumber}</span>
              <button type="button" className="btn btn--guests btn--white" aria-label="add-adult" onClick={addChild}><BsPlus /></button>
            </div>
          </div>
          <div className="dropdown__item dropdown__item--guests">
            <span className="dropdown__item__text">Rooms</span>
            <div className="dropdown__item__buttons">
              <button type="button" className={`btn btn--guests ${rooms > 0 ? 'btn--white' : 'btn--disabled'}`} aria-label="remove-adult" onClick={removeRoom}><RiSubtractFill /></button>
              <span className="dropdown__item__number">{rooms}</span>
              <button type="button" className="btn btn--guests btn--white" aria-label="add-adult" onClick={addRoom}><BsPlus /></button>
            </div>
          </div>
        </div>
      )
        : null}
    </Aux>
  );
};

GuestsDropdown.propTypes = {
  open: PropTypes.bool,
  handleOpen: PropTypes.func.isRequired,
  removeAdult: PropTypes.func.isRequired,
  addAdult: PropTypes.func.isRequired,
  adults: PropTypes.number,
  removeChild: PropTypes.func.isRequired,
  addChild: PropTypes.func.isRequired,
  childrenNumber: PropTypes.number,
  removeRoom: PropTypes.func.isRequired,
  addRoom: PropTypes.func.isRequired,
  rooms: PropTypes.number,
};

GuestsDropdown.defaultProps = {
  open: false,
  adults: 0,
  childrenNumber: 0,
  rooms: 0,
};

export default GuestsDropdown;
