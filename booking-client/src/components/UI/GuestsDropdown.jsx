import React, { useRef } from 'react';
import PropTypes from 'prop-types';
import { BsPlus } from 'react-icons/bs';
import { RiSubtractFill } from 'react-icons/ri';
import useOutsideClick from '../../hooks/useOutsideClick';
import Aux from '../../hoc/Auxiliary';

const GuestsDropdown = ({
  open,
  removeGuest,
  addGuest,
  guests,
  handleOpen,
}) => {
  const wrapperRef = useRef(null);
  useOutsideClick(wrapperRef, handleOpen);

  return (
    <Aux>
      {open ? (
        <div className="dropdown dropdown--guests" ref={open ? wrapperRef : null}>
          <div className="dropdown__item dropdown__item--guests">
            <span className="dropdown__item__text">Guests Number</span>
            <div className="dropdown__item__buttons">
              <button type="button" className={`btn btn--guests ${guests > 0 ? 'btn--white' : 'btn--disabled'}`} aria-label="remove-adult" onClick={removeGuest}><RiSubtractFill /></button>
              <span className="dropdown__item__number">{guests}</span>
              <button type="button" className="btn btn--guests btn--white" aria-label="add-adult" onClick={addGuest}><BsPlus /></button>
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
  removeGuest: PropTypes.func.isRequired,
  addGuest: PropTypes.func.isRequired,
  guests: PropTypes.number,
};

GuestsDropdown.defaultProps = {
  open: false,
  guests: 0,
};

export default GuestsDropdown;
